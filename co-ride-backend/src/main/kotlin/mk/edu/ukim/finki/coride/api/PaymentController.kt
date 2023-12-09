package mk.edu.ukim.finki.coride.api

import com.stripe.model.Charge
import mk.edu.ukim.finki.coride.api.request.ChargeRequest
import mk.edu.ukim.finki.coride.api.request.Payload
import mk.edu.ukim.finki.coride.service.StripeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/payment")
class PaymentController(
        val stripeService: StripeService
) {

    @PostMapping
    fun createPaymentIntent(@RequestBody payload: Payload) {
        // Extract values from the Map
        val frontEndStripeToken = payload.token
        val amount: Long = Math.round(123.0) * 100 //amount;
        val stripeEmail = "aleksovskioliver64@gmail.com"
        val chargeRequest: ChargeRequest = ChargeRequest(amount = amount, stripeToken = frontEndStripeToken, stripeEmail = stripeEmail, description = "test desc", currency = "EUR")
        val charge: Charge = stripeService.charge(chargeRequest)

    }
}
