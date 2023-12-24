package mk.edu.ukim.finki.coride.repository

import mk.edu.ukim.finki.coride.domain.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<Payment, Long>