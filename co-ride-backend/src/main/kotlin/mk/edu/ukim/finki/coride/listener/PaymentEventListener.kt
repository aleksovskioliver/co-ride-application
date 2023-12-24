package mk.edu.ukim.finki.coride.listener

import mk.edu.ukim.finki.coride.event.PaymentCreatedEvent
import mk.edu.ukim.finki.coride.service.PaymentService
import mk.edu.ukim.finki.coride.service.UserService
import org.springframework.context.event.EventListener
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class PaymentEventListener(
        private val paymentService: PaymentService,
        private val userService: UserService
) {

    @EventListener
    fun handlePaymentProcessedEvent(event: PaymentCreatedEvent) {
        // Handle the payment processed event here
        val charge = event.charge
        val user = userService.getUserByEmail(SecurityContextHolder.getContext().authentication.name)!!
        paymentService.save(user, charge.id, charge.amount, charge.currency, charge.status)
    }
}
