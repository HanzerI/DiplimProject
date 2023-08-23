package me.ilya.application.controllers

import me.ilya.application.services.UserService

import java.security.Principal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

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
//            user.socialTokens[me.ilya.application.SocialNetwork.VKONTAKTE] = vkToken
//        }
//        if (!telegramToken.isNullOrEmpty()) {
//            user.socialTokens[me.ilya.application.SocialNetwork.TELEGRAM] = telegramToken
//        }
//
//        userService.saveUser(user)
        return "redirect:/profile"
    }
}
