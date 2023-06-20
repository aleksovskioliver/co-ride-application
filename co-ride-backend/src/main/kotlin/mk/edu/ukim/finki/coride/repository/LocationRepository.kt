package mk.edu.ukim.finki.coride.repository

import mk.edu.ukim.finki.coride.domain.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : JpaRepository<Location, String> {
    fun findByCity(city: String): Location
}