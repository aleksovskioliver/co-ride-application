package mk.edu.ukim.finki.coride.domain

import javax.persistence.*

@Entity
data class Vehicle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val model: String,
    val make: String,
    val seats: Int
)
