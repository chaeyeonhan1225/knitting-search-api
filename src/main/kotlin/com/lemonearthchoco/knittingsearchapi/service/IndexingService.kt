package com.lemonearthchoco.knittingsearchapi.service

import com.lemonearthchoco.knittingsearchapi.domain.KnittingObjectRepository
import com.lemonearthchoco.knittingsearchapi.domain.PatternRepository
import org.springframework.stereotype.Service

@Service
class IndexingService(
    private val patternRepo: PatternRepository,
) {
    fun indexingPatterns() {
        TODO("Indexing Patterns")
    }
}