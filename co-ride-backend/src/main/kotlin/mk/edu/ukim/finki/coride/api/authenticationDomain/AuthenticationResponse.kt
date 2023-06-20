package mk.edu.ukim.finki.coride.api.authenticationDomain

import java.util.Date

data class AuthenticationResponse(val jwt: String, val expiresIn: Date)