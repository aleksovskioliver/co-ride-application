package mk.edu.ukim.finki.coride.api.request

data class ChargeRequest(
        val description: String,
        val amount: Long,
        val currency: String,
        val stripeEmail: String,
        val stripeToken: String
)