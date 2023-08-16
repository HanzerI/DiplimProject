package com.example.demo.services
import com.example.demo.entitys.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.example.demo.services.UserService

@RestController
class UserController(private val userService: UserService) {

    @GetMapping("/users")
    fun getAllUsers(): List<User> {
        return userService.getAllUsers()
    }

    @PostMapping("/users")
    fun addUser(@RequestBody user: User) {
        userService.saveUser(user)
    }
}
