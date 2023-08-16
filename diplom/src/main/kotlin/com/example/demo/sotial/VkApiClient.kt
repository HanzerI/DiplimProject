//package com.example.demo.sotial
//
//import com.vk.api.sdk.VK
//import com.vk.api.sdk.VKApiCallback
//import com.vk.api.sdk.exceptions.VKApiExecutionException
//import com.vk.api.sdk.exceptions.VKApiException
//import com.vk.api.sdk.requests.VKRequest
//import com.vk.api.sdk.requests.wall.WallGetFilter
//import com.vk.api.sdk.requests.wall.WallGetRequest
//import com.vk.api.sdk.responses.wall.WallGetResponse
//
//class VkApiClient(private val accessToken: String) {
//
//    fun getUserPosts(userId: Int, callback: VKApiCallback<WallGetResponse>) {
//        val request = WallGetRequest()
//            .ownerId(userId)
//            .filter(WallGetFilter.ALL)
//            .accessToken(accessToken)
//
//        VK.execute(request, callback)
//    }
//}
