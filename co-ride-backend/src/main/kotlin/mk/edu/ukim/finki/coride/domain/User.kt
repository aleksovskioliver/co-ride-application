package mk.edu.ukim.finki.coride.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Long,
        @Column(name = "first_name")
        val firstName: String,
        @Column(name = "last_name")
        val lastName: String,
        @Column(name = "phone_number")
        val phoneNumber: String,
        @Column(name = "email")
        val email: String,
        @Column(name = "password")
        val password: String,
        @Enumerated(EnumType.STRING)
        @Column(name = "role")
        val role: Role,
        @JsonIgnore
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                name = "reservations_customers",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "reservation_id")]
        )
        val reservations: MutableList<Reservation> = mutableListOf(),
        @OneToOne
        @JoinColumn(name = "vehicle_id")
        var vehicle: Vehicle? = null,
        @ElementCollection
        @CollectionTable(name = "user_ratings", joinColumns = [JoinColumn(name = "user_id")])
        @Column(name = "rating")
        val ratings: MutableList<Int>? = mutableListOf()
) {
        override fun toString(): String {
                return "User(id=$id, username=$firstName, email=$email, ...)"
        }
}
