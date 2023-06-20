package mk.edu.ukim.finki.coride.repository

import mk.edu.ukim.finki.coride.domain.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository : JpaRepository<Vehicle, Long> {

    @Modifying
    @Query("update Vehicle v set v.make = :make,v.model = :model, v.seats = :seats where v.id = :id")
    fun updateVehicle(id: Long, make: String, model: String, seats: Int)
}