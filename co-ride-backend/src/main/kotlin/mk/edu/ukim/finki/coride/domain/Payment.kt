package mk.edu.ukim.finki.coride.domain

import javax.persistence.*

@Entity
@Table(name = "payments")
data class Payment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @ManyToOne
        @JoinColumn(name = "user_id")
        val user: User,

        @Column(name = "transaction_token")
        val transactionToken: String,

        @Column(name = "amount")
        val amount: Long,

        @Column(name = "currency")
        val currency: String,

        @Column(name = "status")
        val status: String
)