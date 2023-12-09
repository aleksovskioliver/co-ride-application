package mk.edu.ukim.finki.coride.api

import mk.edu.ukim.finki.coride.api.request.CreateUserRequest
import mk.edu.ukim.finki.coride.api.response.GetUserResponse
import mk.edu.ukim.finki.coride.api.response.GetUserResponseFailed
import mk.edu.ukim.finki.coride.api.response.GetUserResponseSuccess
import mk.edu.ukim.finki.coride.domain.Reservation
import mk.edu.ukim.finki.coride.domain.User
import mk.edu.ukim.finki.coride.domain.exception.UserAlreadyExists
import mk.edu.ukim.finki.coride.domain.exception.UserNotFoundException
import mk.edu.ukim.finki.coride.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @PostMapping("/create")
    fun createUser(@RequestBody userRequest: CreateUserRequest): ResponseEntity<Any>{
        return try {
            ResponseEntity.ok().body(userService.createUser(userRequest))
        } catch (exception: UserAlreadyExists) {
            ResponseEntity.badRequest().body(exception.message)
        }
    }

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: Long): User {
        return userService.findUserById(id)
    }

    @PutMapping ("/update/{id}")
    fun updateUserById(@PathVariable id: Long, @RequestBody user: CreateUserRequest){
         userService.updateUserById(id,user)
    }

    @GetMapping("/get")
    fun getUser(): ResponseEntity<GetUserResponse> = when (val user = userService.getLoggedInUser()) {
        is GetUserResponseSuccess -> ResponseEntity.ok(user)
        is GetUserResponseFailed -> ResponseEntity.badRequest().body(user)
    }

    @GetMapping("/reservations")
    fun getUserReservations(): MutableList<Reservation> {
        val user = when (val user = userService.getLoggedInUser()) {
            is GetUserResponseSuccess -> user
            is GetUserResponseFailed -> throw UserNotFoundException("User not found")
        }
        return user.user.reservations
    }

}