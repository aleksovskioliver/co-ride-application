package mk.edu.ukim.finki.coride.api.request

data class UpdateReservationRequest(
    val startTime: String,
    val endTime: String,
    val tripCost: Int
)