package mk.edu.ukim.finki.coride.api

import mk.edu.ukim.finki.coride.api.authenticationDomain.AuthenticationRequest
import mk.edu.ukim.finki.coride.api.authenticationDomain.AuthenticationResponse
import mk.edu.ukim.finki.coride.service.MyUsersService
import mk.edu.ukim.finki.coride.util.JwtUtil
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
class AuthenticationController(
    private val myUsersService: MyUsersService,
    private val jwtTokenUtil: JwtUtil,
    private val authenticationManager: AuthenticationManager
) {

    @PostMapping("/authenticate")
    fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<Any> {
        try {
            this.authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(authenticationRequest.email, authenticationRequest.password)
            )
        } catch (exception: Exception) {
            return ResponseEntity.badRequest().body("Invalid username or password")
        }

        val userDetails = this.myUsersService.loadUserByUsername(authenticationRequest.email)
        val jwt = this.jwtTokenUtil.generateToken(userDetails)
        val expiresIn = this.jwtTokenUtil.extractExpiration(jwt)
        return ResponseEntity.ok(AuthenticationResponse(jwt, expiresIn))
    }
}