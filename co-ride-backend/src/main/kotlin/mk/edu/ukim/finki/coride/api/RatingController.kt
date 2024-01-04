package mk.edu.ukim.finki.coride.api

import mk.edu.ukim.finki.coride.api.request.RatingRequest
import mk.edu.ukim.finki.coride.service.RatingService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ratings")
class RatingController(
        private val service: RatingService
) {
    @PostMapping
    fun addRatingToDriver(@RequestBody request: RatingRequest) = service.save(request)
}