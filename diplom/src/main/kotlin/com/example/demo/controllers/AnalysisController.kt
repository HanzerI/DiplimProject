package com.example.demo.controllers

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.client.RestTemplate

@Controller
class AnalysisController(private val restTemplate: RestTemplate) {

    @GetMapping("/analysis")
    fun showAnalysisPage(model: Model): String {
        model.addAttribute("texts", "")
        model.addAttribute("results", mutableListOf<String>())
        return "analysis"
    }

    @PostMapping("/analysis")
    fun analyzeTexts(@RequestParam("texts") texts: String, model: Model): String {
        val url = "http://localhost:5000/analyze"
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val request = HttpEntity(mapOf("texts" to texts.split("\n")), headers)
        val response: ResponseEntity<Map<*, *>> = restTemplate.postForEntity(url, request, Map::class.java)
        @Suppress("UNCHECKED_CAST")
        val results = response.body?.get("results") as? Map<String, Map<String, Double>> ?: emptyMap()
        model.addAttribute("results", results)
        return "analysis"
    }
}
