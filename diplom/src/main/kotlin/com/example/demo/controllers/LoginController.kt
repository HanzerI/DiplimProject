package com.example.demo.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class LoginController {

    @GetMapping("/login")
    fun showLoginForm(): String {
        return "/sign-in/login"
    }
}
