package mk.edu.ukim.finki.coride.repository

import mk.edu.ukim.finki.coride.domain.Location
import mk.edu.ukim.finki.coride.domain.Reservation
import mk.edu.ukim.finki.coride.domain.ReservationStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ReservationRepository : JpaRepository<Reservation,Long>{
    fun findAllByPickupLocationAndStatusAndEndTimeAfter(pickupLocation: Location,reservationStatus: ReservationStatus,endTime: LocalDateTime): List<Reservation>
    fun findALlByDropoutLocationAndStatusAndEndTimeAfter(dropoutLocation: Location,reservationStatus: ReservationStatus,endTime: LocalDateTime): List<Reservation>
    fun findAllByPickupLocationAndDropoutLocationAndStatusAndEndTimeAfter(pickupLocation: Location,dropoutLocation: Location,reservationStatus: ReservationStatus,endTime: LocalDateTime): List<Reservation>
    fun findAllByStatusAndEndTimeAfter(reservationStatus: ReservationStatus,endTime: LocalDateTime): List<Reservation>

    @Modifying
    @Query("update Reservation r set r.startTime = :startTime, r.endTime = :endTime, r.tripCost = :tripCost where r.id = :id")
    fun updateReservation(id: Long, startTime: LocalDateTime,endTime: LocalDateTime,tripCost: Int)
}