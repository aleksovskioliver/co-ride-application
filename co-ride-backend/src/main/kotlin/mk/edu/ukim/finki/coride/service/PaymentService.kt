package mk.edu.ukim.finki.coride.service

import mk.edu.ukim.finki.coride.domain.Payment
import mk.edu.ukim.finki.coride.domain.User
import mk.edu.ukim.finki.coride.repository.PaymentRepository
import org.springframework.stereotype.Service
import javax.persistence.Column

@Service
class PaymentService(
        val repository: PaymentRepository
) {
    fun save(user: User, transactionToken: String, amount: Long, currency: String, status: String ){
        repository.save(Payment(
                id = 0L,
                user = user,
                transactionToken = transactionToken,
                amount =  amount,
                currency = currency,
                status = status
        ))
    }
}