package com.example.demo.controllers

import com.example.demo.entitys.User
import com.example.demo.services.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class RegistrationController(private val userService: UserService) {

    @GetMapping("/register")
    fun showRegistrationForm(model: Model): String {
        model.addAttribute("user", User(null, "", "", "",))
        return "registration"
    }

    @PostMapping("/register")
    fun registerUser(@ModelAttribute("user") user: User, model: Model): String {
        // Check if the username or email is already in use
        if (userService.getUserByUsername(user.username) != null) {
            model.addAttribute("usernameError", "Username is already in use")
            return "registration"
        }

        if (userService.getUserByEmail(user.email) != null) {
            model.addAttribute("emailError", "Email is already in use")
            return "registration"
        }

        // Save the user and redirect to a success page
        userService.createUser(user)
        return "redirect:/login"
    }
}
