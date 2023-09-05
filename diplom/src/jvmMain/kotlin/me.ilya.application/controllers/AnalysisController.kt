package me.ilya.application.controllers

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.ilya.application.Tonality.Companion.toTonality
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@CrossOrigin(origins = ["http://localhost:8081"])
@RestController
class AnalysisController(private val restTemplate: RestTemplate) {
    @PostMapping("/analysis")
    fun analyzeData(@RequestBody data: Map<String, Any>): ResponseEntity<String> {
        val response = mapOf("result" to "Analysis result here")
        return ResponseEntity.ok(Json.encodeToString(response))
    }

}
//@GetMapping("/analysis")
//fun showAnalysisPage(model: Model): String {
//    model.addAttribute("texts", "")
//    model.addAttribute("results", mutableListOf<String>())
//    return "analysis"
//}
//
//@PostMapping("/analysis")
//fun analyzeTexts(@RequestParam("texts") texts: String, model: Model): String {
//    val url = "http://localhost:5000/analyze"
//    val headers = HttpHeaders()
//    headers.contentType = MediaType.APPLICATION_JSON
//    val request = HttpEntity(mapOf("texts" to texts.split("\n")), headers)
//    val response: ResponseEntity<Map<*, *>> = restTemplate.postForEntity(url, request, Map::class.java)
//    @Suppress("UNCHECKED_CAST")
//    val results = (response.body?.get("results") as? Map<String, String> ?: emptyMap()).mapValues { it.value.toTonality() }
//    model.addAttribute("results", results)
//    return "analysis"
//}
