package mk.edu.ukim.finki.coride.repository

import mk.edu.ukim.finki.coride.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>{
    fun findByEmail(email: String?): User?

    @Modifying
    @Query("update User u set u.firstName = :firstName, u.lastName = :lastName, u.phoneNumber = :phoneNumber where u.id = :id")
    fun updateUser(id: Long, firstName: String, lastName: String, phoneNumber: String): Int
}