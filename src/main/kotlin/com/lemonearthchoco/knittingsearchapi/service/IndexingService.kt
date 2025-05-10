package com.lemonearthchoco.knittingsearchapi.service

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders
import com.lemonearthchoco.knittingsearchapi.domain.pattern.Pattern
import com.lemonearthchoco.knittingsearchapi.domain.pattern.PatternRepository
import com.lemonearthchoco.knittingsearchapi.domain.searchkeyword.SearchKeywordRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder
import org.springframework.stereotype.Service

@Service
class IndexingService(
    private val patternRepo: PatternRepository,
    private val keywordRepo: SearchKeywordRepository,
    private val patternDetailSearchUseCase: PatternSearchUseCase
) {
    fun indexingPatternsBulk(): List<Pattern> {
        println("============Indexing Patterns============")
        val keywords = keywordRepo.findSearchKeywordsIfNullPatternIndexedAt()
        keywords.forEach { it.setPatternIndexed() }
        indexingPattern(keywords.map { it.keyword })
        println("=========================================")
        keywordRepo.saveAll(keywords)
        return emptyList()
    }

    private fun indexingPattern(keywords: List<String>) {
        keywords.map {
            val patterns = patternDetailSearchUseCase.findPatterns(
                query = it,
                page = 1,
                pageSize = 100
            )
            println("$it patterns = ${patterns.size}")
            patternRepo.saveAll(patterns)
        }
    }
}