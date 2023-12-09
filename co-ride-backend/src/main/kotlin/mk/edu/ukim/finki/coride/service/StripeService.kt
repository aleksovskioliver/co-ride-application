package mk.edu.ukim.finki.coride.service

import com.stripe.Stripe
import com.stripe.model.Charge
import mk.edu.ukim.finki.coride.api.request.ChargeRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct


@Service
class StripeService {
//    @Value("\${STRIPE_SECRET_KEY}")
//    private val secretKey: String? = null
    @PostConstruct
    fun init() {
        Stripe.apiKey = "sk_test_51Kiay7IDTHvo6Uv1StroqjLzgB1JcAAmlTNjROpsuroEVTj6Rlwd5KpBWgYxQFaPe5873qQx9nZ8eDvRY100Lhtc00KeUjT7BW"
    }

//    @Throws(AuthenticationException::class, InvalidRequestException::class, APIConnectionException::class, CardException::class, APIException::class)
    fun charge(chargeRequest: ChargeRequest): Charge {
        val chargeParams: MutableMap<String, Any> = HashMap()
        chargeParams["amount"] = chargeRequest.amount
        chargeParams["currency"] = chargeRequest.currency
        chargeParams["description"] = chargeRequest.description
        chargeParams["source"] = chargeRequest.stripeToken
        return Charge.create(chargeParams)
    }
}