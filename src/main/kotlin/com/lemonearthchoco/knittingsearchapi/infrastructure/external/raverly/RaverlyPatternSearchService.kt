package com.lemonearthchoco.knittingsearchapi.infrastructure.external.raverly

import com.lemonearthchoco.knittingsearchapi.domain.Pattern
import com.lemonearthchoco.knittingsearchapi.infrastructure.external.raverly.client.RavelryClient
import com.lemonearthchoco.knittingsearchapi.service.PatternSearchUseCase
import org.springframework.stereotype.Service

@Service
class RaverlyPatternSearchService(
    private val ravelryClient: RavelryClient,
): PatternSearchUseCase {
    override fun findPatterns(query: String, page: Int, pageSize: Int): List<Pattern> {
        val searchResult = ravelryClient.findPatterns(query, page, pageSize, false)
        val patterns =  searchResult.patterns?.map { it.toDomain() } ?: emptyList()
        println(patterns.size)
        val patternIds = patterns.map { it.id.replace("raverly:", "").trim() }
        println(patternIds)
        val details = findPatternDetail(patternIds)
        val results = details.patterns.values.map { it.toDomain() }
        println(results.size)
        return results
    }


    fun findPatternDetail(ids: List<String>): PatternDetailSearchResult {
        println(ids.joinToString("+"))
        val details = ravelryClient.findPatternDetailsByIds(ids.joinToString("+"))
        println(details.patterns.keys)
        return details
    }

}