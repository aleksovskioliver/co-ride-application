package mk.edu.ukim.finki.coride.api

import mk.edu.ukim.finki.coride.api.request.CreateVehicleRequest
import mk.edu.ukim.finki.coride.domain.Vehicle
import mk.edu.ukim.finki.coride.domain.exception.VehicleNotFound
import mk.edu.ukim.finki.coride.service.VehicleService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/api/vehicle")
class VehicleController(private val vehicleService: VehicleService) {

    @PostMapping("/create")
    fun createVehicle(@RequestBody vehicleRequest: CreateVehicleRequest){
        vehicleService.createVehicle(vehicleRequest)
    }

    @GetMapping
    fun getVehicles() = vehicleService.getAllVehicles()

    @GetMapping("/{id}")
    fun getVehicleById(@PathVariable id: Long): Vehicle {
        return vehicleService.getVehicleById(id)
    }

    @PutMapping("/{id}")
    fun updateVehicle(@PathVariable id: Long, @RequestBody vehicle: Vehicle): ResponseEntity<Any>{
        return try {
            ok().body(vehicleService.updateUserById(id,vehicle))
        }catch (e: VehicleNotFound){
            ResponseEntity.notFound().build()
        }
    }
}