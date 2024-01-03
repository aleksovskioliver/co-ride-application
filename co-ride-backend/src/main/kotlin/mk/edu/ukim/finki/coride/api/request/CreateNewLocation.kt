package mk.edu.ukim.finki.coride.api.request

data class CreateNewLocation(
        val city: String,
        val lat: Double,
        val lng: Double,
        val country: String
)