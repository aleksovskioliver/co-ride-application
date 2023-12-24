package mk.edu.ukim.finki.coride

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EntityScan(basePackages = ["mk.edu.ukim.finki.coride.domain"])
class CoRideApplication

fun main(args: Array<String>) {
	runApplication<CoRideApplication>(*args)
}
