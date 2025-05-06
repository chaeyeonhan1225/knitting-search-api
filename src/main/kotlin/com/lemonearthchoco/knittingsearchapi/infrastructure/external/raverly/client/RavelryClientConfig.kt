package com.lemonearthchoco.knittingsearchapi.infrastructure.external.raverly.client

import feign.RequestInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import java.util.*

class RavelryClientConfig(
    @Value("\${access.raverly.client_id}") private val clientId: String,
    @Value("\${access.raverly.client_secret}") private val clientSecret: String,
) {
    @Bean
     fun apply(): RequestInterceptor = RequestInterceptor { template ->
        val credentialCode = "$clientId:$clientSecret".trim()
        val encodedCredentials = Base64.getEncoder().withoutPadding().encodeToString(credentialCode.toByteArray())
        println("credentialCode = $credentialCode, encodedCredentials = $encodedCredentials")
        template?.header("Authorization", "Basic $encodedCredentials")
    }
}