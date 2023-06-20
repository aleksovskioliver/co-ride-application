package mk.edu.ukim.finki.coride.security

import mk.edu.ukim.finki.coride.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MyUserDetails(user: User) : UserDetails {

    private val username = user.email
    private val password = user.password
    private val authorities: MutableCollection<GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority(user.role.toString()))

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}