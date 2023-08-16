package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
//	val texts = listOf("Text 1", "Text 2", "Text 3")
//
//	val restTemplate = RestTemplate()
//	val headers = HttpHeaders().apply {
//		contentType = MediaType.APPLICATION_JSON
//	}
//	val requestBody = mapOf("texts" to texts)
//	val httpEntity = HttpEntity(requestBody, headers)
//
//	val response: ResponseEntity<Map<*, *>> = restTemplate.postForEntity(
//		"http://localhost:5000/analyze",
//		httpEntity,
//		Map::class.java
//	)
//
//	val responseBody = response.body
//	println("Response: $responseBody")
}

