package mk.edu.ukim.finki.coride.domain.exception

class UserAlreadyExists(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    constructor(cause: Throwable) : this(null, cause)
}