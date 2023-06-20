package mk.edu.ukim.finki.coride.service

import mk.edu.ukim.finki.coride.domain.exception.UserNotFoundException
import mk.edu.ukim.finki.coride.repository.UserRepository
import mk.edu.ukim.finki.coride.security.MyUserDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MyUsersService(val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByEmail(username) ?: throw UserNotFoundException("User with username $username is not found")
        return MyUserDetails(user)
    }


}