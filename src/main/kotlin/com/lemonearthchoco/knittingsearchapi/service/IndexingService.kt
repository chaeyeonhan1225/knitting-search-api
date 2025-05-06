package com.lemonearthchoco.knittingsearchapi.service

import com.lemonearthchoco.knittingsearchapi.domain.pattern.Pattern
import com.lemonearthchoco.knittingsearchapi.domain.pattern.PatternRepository
import org.springframework.stereotype.Service

@Service
class IndexingService(
    private val patternRepo: PatternRepository,
) {
    fun indexingPatternsBulk(patterns: List<Pattern>): List<Pattern> {
        val result = patternRepo.saveAll(patterns)
        println(result)
        return result.toList()
    }
}