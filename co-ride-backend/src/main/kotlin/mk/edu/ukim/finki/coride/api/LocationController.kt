package mk.edu.ukim.finki.coride.api

import mk.edu.ukim.finki.coride.api.request.CreateNewLocation
import mk.edu.ukim.finki.coride.domain.Location
import mk.edu.ukim.finki.coride.service.LocationService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/api/location")
class LocationController(private val locationService: LocationService) {

    @PostMapping("/create")
    fun create(@RequestBody newLocations: List<CreateNewLocation>){
        newLocations.map {
            newLocation -> locationService.create(newLocation)
        }
    }

    @GetMapping("/getLocations")
    fun getLocations(): List<Location> {
        return locationService.getLocations()
    }
}