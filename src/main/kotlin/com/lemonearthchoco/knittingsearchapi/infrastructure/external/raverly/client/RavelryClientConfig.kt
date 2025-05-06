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
        template?.header("Authorization", "Basic $encodedCredentials")

        // 내부에서 사용될 API이므로 +는 escape 되지 않도록 수정
        val newUrl = template.url().replace("%2B", "+")
        template?.uri(newUrl)
    }
}