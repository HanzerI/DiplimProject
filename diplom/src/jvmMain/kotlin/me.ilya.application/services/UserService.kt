package me.ilya.application.services


import me.ilya.application.entitys.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User as SpringUser

@Service
class UserService(val userRepository: UserRepository,val passwordEncoder: PasswordEncoder):UserDetailsService {

    fun createUser(user: User) {
        user.password = passwordEncoder.encode(user.password)
        user.roles.add("ROLE_USER") // Add the ROLE_USER role
        saveUser(user)
    }

    fun getUserByUsername(username: String): User? = userRepository.findByUsername(username)

    fun getUserByEmail(email: String): User? = userRepository.findByEmail(email)

    override fun loadUserByUsername(username: String): UserDetails? = userRepository.findByUsername(username)
        ?.let { CustomUserDetails(it) }

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun saveUser(user: User) = userRepository.save(user)

    class CustomUserDetails(val user:User): UserDetails{
        override fun getAuthorities(): MutableCollection<out GrantedAuthority> = user.roles.map{SimpleGrantedAuthority(it)}.toMutableSet()

        override fun getPassword(): String = user.password

        override fun getUsername(): String = user.username

        override fun isAccountNonExpired(): Boolean = true

        override fun isAccountNonLocked(): Boolean = true

        override fun isCredentialsNonExpired(): Boolean = true

        override fun isEnabled(): Boolean = true
    }


}
