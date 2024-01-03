package mk.edu.ukim.finki.coride.api.request

data class Payload(
        val token: String,
        val name: String,
        val reservationId: Long
)
