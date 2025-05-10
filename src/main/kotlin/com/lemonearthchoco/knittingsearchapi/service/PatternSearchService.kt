package com.lemonearthchoco.knittingsearchapi.service

import com.lemonearthchoco.knittingsearchapi.domain.pattern.Pattern
import com.lemonearthchoco.knittingsearchapi.domain.pattern.PatternSearchRepository
import com.lemonearthchoco.knittingsearchapi.domain.searchkeyword.SearchKeywordRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class PatternSearchService(
    private val searchKeywordRepo: SearchKeywordRepository,
    @Qualifier("patternSearchElRepository")
    private val patternSearchInternalRepo: PatternSearchRepository,
    @Qualifier("raverlyPatternSearchRepository")
    private val patternSearchExternalRepo: PatternSearchRepository
): PatternSearchUseCase {
    override fun findPatterns(query: String, page: Int, pageSize: Int): List<Pattern> {
        val keyword = searchKeywordRepo.findById(query)

        return if (keyword.getOrNull()?.patternIndexedAt != null) {
            patternSearchInternalRepo.search(query, page, pageSize)
        } else {
            patternSearchExternalRepo.search(query, page, pageSize)
        }
    }
}
