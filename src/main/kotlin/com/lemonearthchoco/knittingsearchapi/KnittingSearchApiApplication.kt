package com.lemonearthchoco.knittingsearchapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@EnableAsync
@EnableFeignClients
@EnableScheduling
@SpringBootApplication
class KnittingSearchApiApplication

fun main(args: Array<String>) {
    runApplication<KnittingSearchApiApplication>(*args)
}
