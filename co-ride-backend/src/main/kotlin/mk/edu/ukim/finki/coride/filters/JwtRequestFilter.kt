package mk.edu.ukim.finki.coride.filters

import mk.edu.ukim.finki.coride.service.MyUsersService
import mk.edu.ukim.finki.coride.util.JwtUtil
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(private val jwtUtil: JwtUtil, private val myUsersService: MyUsersService) :
    OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")
        val username: String?
        val jwt: String?
        when (!authorizationHeader.isNullOrBlank() && authorizationHeader.startsWith("Bearer ")) {
            true -> {
                jwt = authorizationHeader.substring(7)
                username = jwtUtil.extractUsername(jwt)
            }
            else -> {
                jwt = null
                username = null
            }
        }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = this.myUsersService.loadUserByUsername(username)
            if (jwtUtil.validateToken(jwt, userDetails)) {
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        filterChain.doFilter(request, response)

    }
}