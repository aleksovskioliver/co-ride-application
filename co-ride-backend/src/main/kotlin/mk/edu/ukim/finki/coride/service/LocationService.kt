package mk.edu.ukim.finki.coride.service

import mk.edu.ukim.finki.coride.api.request.CreateNewLocation
import mk.edu.ukim.finki.coride.domain.Location
import mk.edu.ukim.finki.coride.repository.LocationRepository
import org.springframework.stereotype.Service

@Service
class LocationService(private val locationRepository: LocationRepository) {

    fun getLocations(): List<Location>{
        return locationRepository.findAll()
    }

    fun create(newLocation: CreateNewLocation): Location{
        return locationRepository.save(Location(newLocation.city,newLocation.lat,newLocation.lng,newLocation.country))
    }

}