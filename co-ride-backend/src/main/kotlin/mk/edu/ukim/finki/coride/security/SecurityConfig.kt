package mk.edu.ukim.finki.coride.security

import mk.edu.ukim.finki.coride.filters.JwtRequestFilter
import mk.edu.ukim.finki.coride.service.MyUsersService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(val myUsersService: MyUsersService, val jwtRequestFilter: JwtRequestFilter) :
    WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(myUsersService)?.passwordEncoder(getPasswordEncoder())
    }

    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.antMatchers(HttpMethod.OPTIONS, "/**")
    }

    override fun configure(http: HttpSecurity?) {
        http?.cors()?.and()?.csrf()?.disable()
            ?.authorizeRequests()
            ?.antMatchers("/api/reservation/create")?.hasAnyRole("DRIVER")
            ?.antMatchers("/api/vehicle/create")?.hasAnyRole("DRIVER")
//            ?.antMatchers("/api/reservation/addCustomer")?.hasAnyRole("DRIVER","CUSTOMER")
//            ?.antMatchers("/api/reservation/findAll")?.permitAll()
            ?.antMatchers("/api/reservation/**")?.permitAll()
            ?.antMatchers("/api/user/**")?.permitAll()
            ?.antMatchers("/api/location/create")?.permitAll()
            ?.antMatchers("/authenticate")?.permitAll()
            ?.antMatchers("/api/vehicle")?.permitAll()
            ?.and()
            ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http?.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun getPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(10)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            allowedOrigins = listOf("http://localhost:4200") // Replace with your frontend URL
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
            allowCredentials = true
            maxAge = 3600
        }
        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/api/**", configuration) // Adjust the mapping based on your API structure
        }
    }

}