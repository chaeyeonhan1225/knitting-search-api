package com.lemonearthchoco.knittingsearchapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@EnableFeignClients
@SpringBootApplication
class KnittingSearchApiApplication

fun main(args: Array<String>) {
    runApplication<KnittingSearchApiApplication>(*args)
}
