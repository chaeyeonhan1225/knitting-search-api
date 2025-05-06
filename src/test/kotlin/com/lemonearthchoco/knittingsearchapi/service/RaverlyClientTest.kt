package com.lemonearthchoco.knittingsearchapi.service

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.test.context.TestConstructor
import java.util.*

class RaverlyClientTest {
    @Test
    fun testRaverlyClient() {
        val credentialCode = "read-1929324fb02713b35907078b6ecc1338:TOy+ET2GpfcGAVNCye9+ZaE5dZZaI13LiJHoqaVu"
        val encodedCredentials = Base64.getEncoder().withoutPadding().encodeToString(credentialCode.toByteArray())
        println(encodedCredentials)
    }
}