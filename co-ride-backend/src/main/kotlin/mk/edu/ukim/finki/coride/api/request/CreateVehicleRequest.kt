package mk.edu.ukim.finki.coride.api.request

data class CreateVehicleRequest(
        val model: String,
        val make: String,
        val seats: Int
)