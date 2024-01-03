package mk.edu.ukim.finki.coride.service

import mk.edu.ukim.finki.coride.api.request.RatingRequest
import mk.edu.ukim.finki.coride.domain.Rating
import mk.edu.ukim.finki.coride.domain.User
import mk.edu.ukim.finki.coride.repository.RatingRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class RatingService(
        private val repository: RatingRepository,
        private val userService: UserService
) {
    fun save(request: RatingRequest): Rating {
        val driver = userService.findUserById(request.driverId)
        val rider = userService.getUserByEmail(SecurityContextHolder.getContext().authentication.name)!!
        return repository.save(Rating(
                id = 0L,
                driver = driver,
                score = request.score,
                rider = rider
        ))
    }

    fun getAverageRatingForDriver(driver: User) = repository.findAllByDriver(driver)
            .map { it.score }
            .takeIf { it.isNotEmpty() }
            ?.average()
            ?: 0.0

}