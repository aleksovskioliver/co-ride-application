package mk.edu.ukim.finki.coride.domain

import javax.persistence.*

@Entity
@Table(name = "vehicles")
data class Vehicle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long,
    @Column(name = "model")
    val model: String,
    @Column(name = "make")
    val make: String,
    @Column(name = "seats")
    val seats: Int
)
