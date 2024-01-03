package mk.edu.ukim.finki.coride.service

import com.stripe.Stripe
import com.stripe.model.Charge
import mk.edu.ukim.finki.coride.api.request.ChargeRequest
import mk.edu.ukim.finki.coride.api.request.Payload
import mk.edu.ukim.finki.coride.event.PaymentCreatedEvent
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct


@Service
class StripeService(
        private val applicationEventPublisher: ApplicationEventPublisher,
        private val reservationService: ReservationService
) {
    @Value("\${stripe.secret-key}")
    private val stripeSecretKey: String? = null

    @PostConstruct
    fun init() {
        Stripe.apiKey = stripeSecretKey
    }

    fun charge(payload: Payload): Charge {
        val reservation = reservationService.getReservationById(payload.reservationId)
        val amount: Long = reservation.tripCost.toLong()
        val chargeRequest = ChargeRequest(amount = amount,
                stripeToken = payload.token,
                description = "CoRide Payment",
                currency = "MKD")

        val chargeParams: MutableMap<String, Any> = HashMap()
        chargeParams["amount"] = chargeRequest.amount
        chargeParams["currency"] = chargeRequest.currency
        chargeParams["description"] = chargeRequest.description
        chargeParams["source"] = chargeRequest.stripeToken

        val charge = Charge.create(chargeParams)

        val paymentCreatedEvent = PaymentCreatedEvent(this, charge)
        applicationEventPublisher.publishEvent(paymentCreatedEvent)
        return charge
    }
}