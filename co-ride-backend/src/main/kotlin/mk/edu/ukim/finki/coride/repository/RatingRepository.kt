package mk.edu.ukim.finki.coride.repository

import mk.edu.ukim.finki.coride.domain.Rating
import mk.edu.ukim.finki.coride.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface RatingRepository : JpaRepository<Rating, Long>{
    fun findAllByDriver(driver: User): List<Rating>
    fun findByDriverAndRider(driver: User, rider: User): Rating?
}