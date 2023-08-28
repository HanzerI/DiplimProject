package me.ilya.application.controllers

import com.vk.api.sdk.client.TransportClient
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.UserActor
import com.vk.api.sdk.httpclient.HttpTransportClient
import me.ilya.application.SocialNetwork
import me.ilya.application.entitys.User.Companion.sotialId
import me.ilya.application.entitys.User.Companion.getToken
import me.ilya.application.services.UserService

import java.security.Principal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.net.URLEncoder

@Controller
class ProfileController(private val userService: UserService) {

    @GetMapping("/profile")
    fun showProfilePage(model: Model, principal: Principal): String {
        val username = principal.name
        val user = userService.getUserByUsername(username)!!
        val userActor = UserActor(user.sotialId(SocialNetwork.VKONTAKTE)?.toInt() ,user.getToken(SocialNetwork.VKONTAKTE))

        val transportClient: TransportClient = HttpTransportClient()
        val vk = VkApiClient(transportClient)
        val user2 = vk.users().get(userActor).execute()[0]
        val fullName = "${user2.firstName} ${user2.lastName}"
        val encodedFullName = URLEncoder.encode(fullName, "UTF-8")

        model.addAttribute("user", fullName)
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
