package com.lemonearthchoco.knittingsearchapi.infrastructure.external.raverly.client

import com.lemonearthchoco.knittingsearchapi.infrastructure.external.raverly.PatternDetailSearchResult
import com.lemonearthchoco.knittingsearchapi.infrastructure.external.raverly.PatternSearchResult
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "ravelry-client", url = "\${access.raverly.base_url}", configuration = [RavelryClientConfig::class])
interface RavelryClient {
    @GetMapping("/patterns/search.json")
    fun findPatterns(
        @RequestParam query: String,
        @RequestParam page: Int,
        @RequestParam page_size: Int,
        @RequestParam personal_attributes: Boolean
    ): PatternSearchResult

    @GetMapping("/patterns.json")
    fun findPatternDetailsByIds(@RequestParam ids: String): PatternDetailSearchResult
}