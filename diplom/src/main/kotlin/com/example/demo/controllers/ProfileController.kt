package com.example.demo.controllers


import com.example.demo.services.UserService
import com.example.demo.sotial.SocialNetwork
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.security.Principal
import com.example.demo.entitys.User
import com.example.demo.services.UserRepository
import org.springframework.security.core.userdetails.UserDetails

@Controller
class ProfileController(private val userService: UserService) {

    @GetMapping("/profile")
    fun showProfilePage(model: Model, principal: Principal): String {
        val username = principal.name
        val user = userService.loadUserByUsername(username)
        model.addAttribute("user", user)
        return "profile"
    }

    @PostMapping("/profile/add-tokens")
    fun addTokens(@RequestParam("vkToken") vkToken: String?, @RequestParam("telegramToken") telegramToken: String?, principal: Principal): String {
        val username = principal.name
//        val user: UserDetails = userService.loadUserByUsername(username)
//
//        if (!vkToken.isNullOrEmpty()) {
//            user.socialTokens[SocialNetwork.VKONTAKTE] = vkToken
//        }
//        if (!telegramToken.isNullOrEmpty()) {
//            user.socialTokens[SocialNetwork.TELEGRAM] = telegramToken
//        }
//
//        userService.saveUser(user)
        return "redirect:/profile"
    }
}
