package com.lemonearthchoco.knittingsearchapi.infrastructure.external.raverly

import com.lemonearthchoco.knittingsearchapi.domain.event.PatternSearched
import com.lemonearthchoco.knittingsearchapi.domain.pattern.Pattern
import com.lemonearthchoco.knittingsearchapi.infrastructure.external.raverly.client.RavelryClient
import com.lemonearthchoco.knittingsearchapi.service.DomainEventPublisher
import com.lemonearthchoco.knittingsearchapi.service.IndexingService
import com.lemonearthchoco.knittingsearchapi.service.PatternSearchUseCase
import org.springframework.stereotype.Service

@Service
class RaverlyPatternSearchService(
    private val ravelryClient: RavelryClient,
    // private val indexingService: IndexingService,
    private val eventPublisher: DomainEventPublisher
): PatternSearchUseCase {
    override fun findPatterns(query: String, page: Int, pageSize: Int): List<Pattern> {
        val searchResult = ravelryClient.findPatterns(query, page, pageSize, false)
        val patterns =  searchResult.patterns?.map { it.toDomain() } ?: emptyList()
        val patternIds = patterns.map { it.id.replace("raverly:", "").trim() }

        if (patternIds.isEmpty()) {
            throw IllegalArgumentException("Cannot find pattern with id: $query")
        }

        val details = findPatternDetail(patternIds)
        val results = details.patterns.values.map { it.toDomain() }

        // indexingService.indexingPatternsBulk(results)

        eventPublisher.publish(PatternSearched(keyword = query))
        return results
    }


    fun findPatternDetail(ids: List<String>): PatternDetailSearchResult = ravelryClient.findPatternDetailsByIds(ids.joinToString("+"))

}