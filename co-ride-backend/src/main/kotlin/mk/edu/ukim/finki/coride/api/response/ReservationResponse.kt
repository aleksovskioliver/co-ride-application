package mk.edu.ukim.finki.coride.api.response

import mk.edu.ukim.finki.coride.domain.Reservation

class ReservationResponse(
        val reservation: Reservation,
        val rating: Double
)