package mk.edu.ukim.finki.coride.domain

import java.time.LocalDateTime
import javax.persistence.*
import mk.edu.ukim.finki.coride.domain.User

@Entity
@Table(name = "reservations")
data class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "driver_id")
    val driver: User,
    @ManyToMany
    @JoinTable(
            name = "reservation_customers",
            joinColumns = [JoinColumn(name = "reservation_id")],
            inverseJoinColumns = [JoinColumn(name = "customer_id")]
    )
    val customers: MutableList<User>,
    @Column(name = "start_time")
    val startTime: LocalDateTime,
    @Column(name = "end_time")
    val endTime: LocalDateTime,
    @OneToOne
    @JoinColumn(name = "pickup_location")
    val pickupLocation: Location,
    @OneToOne
    @JoinColumn(name = "dropout_location")
    val dropoutLocation: Location,
    @Column(name = "trip_cost")
    val tripCost: Int,
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    val status: ReservationStatus,
    @Column(name = "available_seats")
    val availableSeats: Int
)
