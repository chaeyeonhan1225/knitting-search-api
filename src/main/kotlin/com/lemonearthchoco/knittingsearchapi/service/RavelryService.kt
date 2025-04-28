package com.lemonearthchoco.knittingsearchapi.service

import com.lemonearthchoco.knittingsearchapi.domain.Pattern
import org.apache.logging.log4j.util.Base64Util
import org.bouncycastle.util.encoders.Base64Encoder
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import java.util.*

@Service
class RavelryService(
    @Value("\${access.raverly.client_id}") private val clientId: String,
    @Value("\${access.raverly.client_secret}") private val clientSecret: String,
) {
    private val token
        get() = ""

    fun getToken(code: String): Any? {
        val url = "https://www.ravelry.com/oauth2/token"

        val credentialCode = "$clientId:$clientSecret"
        val encodedCredentials = Base64.getEncoder().withoutPadding().encodeToString(credentialCode.toByteArray())

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            set("Authorization", "Basic $encodedCredentials")
        }

        val body: MultiValueMap<String, String> = LinkedMultiValueMap<String, String>().apply {
            add("grant_type", "authorization_code")
            add("code", code)
            add("redirect_uri", "")
            add("state", "chaeyeon123")
        }

        val request = HttpEntity(body, headers)

        val result = runCatching {
            restTemplate.postForObject(url, request, String::class.java)
        }.getOrElse { e ->
            println(e.printStackTrace())
        }
        println("result = $result")
        return result
    }

    fun findPatterns(): List<Pattern> {

        return emptyList()
    }

    companion object {
        val restTemplate = RestTemplate().apply { messageConverters.add(FormHttpMessageConverter()) }
    }
}