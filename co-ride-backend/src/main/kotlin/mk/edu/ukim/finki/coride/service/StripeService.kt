package mk.edu.ukim.finki.coride.service

import com.stripe.Stripe
import com.stripe.model.Charge
import mk.edu.ukim.finki.coride.api.request.ChargeRequest
import mk.edu.ukim.finki.coride.event.PaymentCreatedEvent
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct


@Service
class StripeService(
        private val applicationEventPublisher: ApplicationEventPublisher
) {
    @Value("\${stripe.secret-key}")
    private val stripeSecretKey: String? = null

    @PostConstruct
    fun init() {
        Stripe.apiKey = stripeSecretKey
    }

    fun charge(chargeRequest: ChargeRequest): Charge {
        val chargeParams: MutableMap<String, Any> = HashMap()
//        chargeParams["email"] = chargeRequest.stripeEmail
        chargeParams["amount"] = chargeRequest.amount
        chargeParams["currency"] = chargeRequest.currency
        chargeParams["description"] = chargeRequest.description
        chargeParams["source"] = chargeRequest.stripeToken

        val charge = Charge.create(chargeParams)

        // Publish the PaymentProcessedEvent with the charge result
        val paymentCreatedEvent = PaymentCreatedEvent(this, charge)
        applicationEventPublisher.publishEvent(paymentCreatedEvent)
        return charge
    }
}