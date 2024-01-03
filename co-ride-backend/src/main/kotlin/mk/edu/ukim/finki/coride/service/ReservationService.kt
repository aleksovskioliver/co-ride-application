package mk.edu.ukim.finki.coride.service

import mk.edu.ukim.finki.coride.api.request.CreateReservationRequest
import mk.edu.ukim.finki.coride.api.request.UpdateReservationRequest
import mk.edu.ukim.finki.coride.api.response.ReservationResponse
import mk.edu.ukim.finki.coride.domain.*
import mk.edu.ukim.finki.coride.domain.exception.CustomerAlreadyReservedException
import mk.edu.ukim.finki.coride.domain.exception.ReservationNotFound
import mk.edu.ukim.finki.coride.repository.LocationRepository
import mk.edu.ukim.finki.coride.repository.UserRepository
import mk.edu.ukim.finki.coride.repository.ReservationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class ReservationService(
        val reservationRepository: ReservationRepository,
        val userRepository: UserRepository,
        val locationRepository: LocationRepository,
        val ratingService: RatingService
) {

    fun getReservations(): List<Reservation> {
        return reservationRepository.findAll()
    }

    fun getActiveReservation(): List<ReservationResponse> {
        return reservationRepository.findAllByStatusAndEndTimeAfter(ReservationStatus.ACTIVE, LocalDateTime.now())
                .sortedByDescending {
                    ratingService.getAverageRatingForDriver(it.driver)
                }.map { ReservationResponse(reservation = it, rating = ratingService.getAverageRatingForDriver(it.driver)) }

    }

    fun getReservationById(id: Long): Reservation {
        return reservationRepository.findByIdOrNull(id) ?: throw ReservationNotFound("Reservation is not created")
    }

    fun getDriverCreatedReservations(): List<Reservation> {
        val driver = userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)
        return getReservations().filter { r -> r.driver.id == driver?.id }
    }

    fun createReservation(newReservationRequest: CreateReservationRequest): Reservation {
        val driver = userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)!!
        val startTime = LocalDateTime.parse(newReservationRequest.startTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val endTime = LocalDateTime.parse(newReservationRequest.endTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val pickupLocation = locationRepository.findByCity(newReservationRequest.pickupLocation)
        val dropoutLocation = locationRepository.findByCity(newReservationRequest.dropoutLocation)
        return reservationRepository.save(
                Reservation(
                        0, driver, mutableListOf<User>(), startTime, endTime, pickupLocation, dropoutLocation,
                        newReservationRequest.tripCost, ReservationStatus.ACTIVE, newReservationRequest.availableSeats
                )
        )
    }

    fun addCustomerToReservation(reservationId: Long): Reservation {
        val reservation = reservationRepository.findByIdOrNull(reservationId)
                ?: throw ReservationNotFound("Reservation with id $reservationId is not found")
        val customer = userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)!!
        if (reservation.customers.contains(customer)) throw CustomerAlreadyReservedException("You have already reserved this reservation!")
        reservation.customers.add(customer)
        val savedReservation = reservationRepository.save(
                Reservation(
                        reservation.id, reservation.driver, reservation.customers,
                        reservation.startTime, reservation.endTime, reservation.pickupLocation, reservation.dropoutLocation,
                        reservation.tripCost,
                        if (reservation.availableSeats <= 1) ReservationStatus.FINISHED else ReservationStatus.ACTIVE,
                        reservation.availableSeats - 1
                )
        )
        customer.reservations.add(savedReservation)
        userRepository.save(customer)
        return savedReservation
    }

    fun removeCustomerFromReservation(reservationId: Long): Reservation {
        val reservation = reservationRepository.findByIdOrNull(reservationId)
                ?: throw ReservationNotFound("Reservation with id $reservationId is not found")
        val customer = userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)!!
        reservation.customers.remove(customer)
        val savedReservation = reservationRepository.save(
                Reservation(
                        reservation.id, reservation.driver, reservation.customers,
                        reservation.startTime, reservation.endTime, reservation.pickupLocation, reservation.dropoutLocation,
                        reservation.tripCost,
                        if (reservation.availableSeats >= 0) ReservationStatus.ACTIVE else ReservationStatus.FINISHED,
                        reservation.availableSeats + 1
                )
        )
        customer.reservations.remove(savedReservation)
        userRepository.save(customer)
        return savedReservation
    }


    fun deleteReservation(id: Long) {
        val reservation = reservationRepository.findByIdOrNull(id)
                ?: throw ReservationNotFound("Reservation with id $id is not found")
        reservationRepository.delete(reservation)
    }

    fun filterReservationByPickupLocation(city: String): List<ReservationResponse> {
        val pickupLocation = locationRepository.findByCity(city)
        return reservationRepository.findAllByPickupLocationAndStatusAndEndTimeAfter(pickupLocation, ReservationStatus.ACTIVE, LocalDateTime.now())
                .sortedByDescending {
                    ratingService.getAverageRatingForDriver(it.driver)
                }.map { ReservationResponse(reservation = it, rating = ratingService.getAverageRatingForDriver(it.driver)) }
    }

    fun filterReservationByDropoutLocation(city: String): List<ReservationResponse> {
        val dropoutLocation = locationRepository.findByCity(city)
        return reservationRepository.findALlByDropoutLocationAndStatusAndEndTimeAfter(dropoutLocation, ReservationStatus.ACTIVE,
                LocalDateTime.now())
                .sortedByDescending {
                    ratingService.getAverageRatingForDriver(it.driver)
                }.map { ReservationResponse(reservation = it, rating = ratingService.getAverageRatingForDriver(it.driver)) }

    }

    fun filterReservationByPickupLocationAndDropoutLocation(
            pickupCity: String,
            dropoutCity: String,
    ): List<ReservationResponse> {
        val pickupLocation = locationRepository.findByCity(pickupCity)
        val dropoutLocation = locationRepository.findByCity(dropoutCity)
        return reservationRepository.findAllByPickupLocationAndDropoutLocationAndStatusAndEndTimeAfter(
                pickupLocation,
                dropoutLocation,
                ReservationStatus.ACTIVE,
                LocalDateTime.now()
        ).sortedByDescending {
            ratingService.getAverageRatingForDriver(it.driver)
        }.map { ReservationResponse(reservation = it, rating = ratingService.getAverageRatingForDriver(it.driver)) }

    }

    @Transactional
    fun updateReservationById(id: Long, reservation: UpdateReservationRequest) {
        val startTime = LocalDateTime.parse(reservation.startTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val endTime = LocalDateTime.parse(reservation.endTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        reservationRepository.updateReservation(id, startTime, endTime, reservation.tripCost)
    }
}