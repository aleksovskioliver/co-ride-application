package mk.edu.ukim.finki.coride.event

import com.stripe.model.Charge
import org.springframework.context.ApplicationEvent


class PaymentCreatedEvent(source: Any, val charge: Charge) : ApplicationEvent(source)
