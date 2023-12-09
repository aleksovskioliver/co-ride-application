package mk.edu.ukim.finki.coride.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "reservations")
data class Reservation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Long,
//        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "driver_id")
        val driver: User,
        @ManyToMany(fetch = FetchType.LAZY, mappedBy = "reservations")
        val customers: MutableList<User> = mutableListOf(),
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
