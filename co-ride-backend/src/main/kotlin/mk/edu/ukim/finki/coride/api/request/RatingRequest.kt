package mk.edu.ukim.finki.coride.api.request

data class RatingRequest(
        val driverId: Long,
        val score: Double
)
