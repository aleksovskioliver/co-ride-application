package mk.edu.ukim.finki.coride.service

import mk.edu.ukim.finki.coride.api.request.CreateVehicleRequest
import mk.edu.ukim.finki.coride.domain.Vehicle
import mk.edu.ukim.finki.coride.domain.exception.VehicleNotFound
import mk.edu.ukim.finki.coride.repository.VehicleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VehicleService(
    val vehicleRepository: VehicleRepository) {

    fun createVehicle(newVehicle: CreateVehicleRequest): Vehicle{
        return vehicleRepository.save(Vehicle(0, newVehicle.model, newVehicle.make, newVehicle.seats))
    }

    fun getAllVehicles() = vehicleRepository.findAll()

    fun getVehicleById(id: Long): Vehicle {
        return vehicleRepository.findByIdOrNull(id) ?: throw VehicleNotFound("Vehicle with id $id is not found")
    }

    @Transactional
    fun updateUserById(id: Long, vehicle: Vehicle){
        vehicleRepository.updateVehicle(id,vehicle.make,vehicle.model,vehicle.seats)
    }

}