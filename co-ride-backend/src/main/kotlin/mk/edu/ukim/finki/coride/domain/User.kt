package mk.edu.ukim.finki.coride.domain

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
        @ManyToMany(mappedBy = "customers", fetch = FetchType.EAGER)
        val reservation: MutableList<Reservation> = mutableListOf(),
        @OneToOne
        @JoinColumn(name = "vehicle_id")
        var vehicle: Vehicle? = null,
        @ElementCollection
        @CollectionTable(name = "user_ratings", joinColumns = [JoinColumn(name = "user_id")])
        @Column(name = "rating")
        val ratings: MutableList<Int>? = mutableListOf()
)