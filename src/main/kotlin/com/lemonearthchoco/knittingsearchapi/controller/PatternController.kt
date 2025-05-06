package com.lemonearthchoco.knittingsearchapi.controller

import com.lemonearthchoco.knittingsearchapi.domain.pattern.Pattern
import com.lemonearthchoco.knittingsearchapi.service.PatternSearchUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/knitting/patterns")
class PatternController(
    private val patternSearchService: PatternSearchUseCase
) {
    @GetMapping("/search")
    fun search(@RequestParam("query") query: String): List<Pattern> {
        return patternSearchService.findPatterns(query = query, page = 1, pageSize = 10)
    }
}