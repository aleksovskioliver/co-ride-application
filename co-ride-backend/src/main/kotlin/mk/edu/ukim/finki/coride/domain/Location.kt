package mk.edu.ukim.finki.coride.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "locations")
data class Location(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Long,
        @Column(name = "city")
        val city: String,
        @Column(name = "lat")
        val lat: Double,
        @Column(name = "lng")
        val lng: Double,
        @Column(name = "country")
        val country: String
)
