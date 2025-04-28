package com.lemonearthchoco.knittingsearchapi.service

import com.lemonearthchoco.knittingsearchapi.domain.Pattern
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "ravelry-client", url = "\${api.base.url}")
interface RavelryClient {
    @GetMapping("/patterns")
    fun findPatterns(): List<Pattern>
}