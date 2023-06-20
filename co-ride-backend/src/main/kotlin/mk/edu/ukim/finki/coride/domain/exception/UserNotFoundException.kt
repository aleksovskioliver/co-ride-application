package mk.edu.ukim.finki.coride.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(private val errorMessage: String): RuntimeException(errorMessage) {
    override fun toString(): String {
        return this.errorMessage
    }
}