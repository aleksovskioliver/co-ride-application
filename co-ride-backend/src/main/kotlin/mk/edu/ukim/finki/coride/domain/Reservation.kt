package mk.edu.ukim.finki.coride.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne
    val driver: User,
    @ManyToMany
    val customers: MutableList<User>,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    @OneToOne
    val pickupLocation: Location,
    @OneToOne
    val dropoutLocation: Location,
    val tripCost: Int,
    @Enumerated(value = EnumType.STRING)
    val status: ReservationStatus,
    val availableSeats: Int
)
