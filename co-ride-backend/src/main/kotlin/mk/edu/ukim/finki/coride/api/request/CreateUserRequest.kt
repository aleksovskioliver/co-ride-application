package mk.edu.ukim.finki.coride.api.request

data class CreateUserRequest(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val role: String,
    val vehicle: CreateVehicleRequest?
)