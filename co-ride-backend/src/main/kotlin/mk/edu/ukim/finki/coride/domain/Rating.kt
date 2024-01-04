package mk.edu.ukim.finki.coride.domain

import javax.persistence.*

@Entity
@Table(name = "ratings")
data class Rating(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @ManyToOne
        @JoinColumn(name = "driver_id")
        val driver: User,

        @Column(name = "score")
        val score: Double,

        @ManyToOne
        @JoinColumn(name = "rider_id")
        val rider: User,
)
