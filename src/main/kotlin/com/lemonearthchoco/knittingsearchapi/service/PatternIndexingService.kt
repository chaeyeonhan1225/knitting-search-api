package com.lemonearthchoco.knittingsearchapi.service

import com.lemonearthchoco.knittingsearchapi.domain.pattern.Pattern
import com.lemonearthchoco.knittingsearchapi.domain.pattern.PatternRepository
import com.lemonearthchoco.knittingsearchapi.domain.searchkeyword.SearchKeywordRepository
import org.springframework.stereotype.Service

@Service
class PatternIndexingService(
    private val patternRepo: PatternRepository,
    private val keywordRepo: SearchKeywordRepository,
    private val patternSearchService: PatternSearchUseCase
) {
    fun indexingPatternsBulk(): List<Pattern> {
        println("============Indexing Patterns============")
        val keywords = keywordRepo.findSearchKeywordsIfNullPatternIndexedAt()
        indexingPattern(keywords.map { it.keyword })
        println("keywords: $keywords")
        println("=========================================")
        keywords.forEach { it.setPatternIndexed() }
        keywordRepo.saveAll(keywords)
        return emptyList()
    }

    private fun indexingPattern(keywords: List<String>) {
        keywords.map {
            val patterns = patternSearchService.findPatterns(
                query = it,
                page = 1,
                pageSize = 100
            )
            println("$it patterns = ${patterns.size}")
            patternRepo.saveAll(patterns)
        }
    }
}