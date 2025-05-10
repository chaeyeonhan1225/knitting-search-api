package com.lemonearthchoco.knittingsearchapi.service

import com.lemonearthchoco.knittingsearchapi.domain.event.PatternSearched
import com.lemonearthchoco.knittingsearchapi.domain.pattern.Pattern
import com.lemonearthchoco.knittingsearchapi.domain.pattern.PatternSearchRepository
import com.lemonearthchoco.knittingsearchapi.domain.searchkeyword.SearchKeywordRepository
import com.lemonearthchoco.knittingsearchapi.infrastructure.elasticsearch.PatternSearchElRepository
import com.lemonearthchoco.knittingsearchapi.infrastructure.external.raverly.PatternDetailSearchResult
import com.lemonearthchoco.knittingsearchapi.infrastructure.external.raverly.client.RavelryClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

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

        return if (keyword.isPresent) {
            patternSearchInternalRepo.search(query, page, pageSize)
        } else {
            patternSearchExternalRepo.search(query, page, pageSize)
        }
    }
}
