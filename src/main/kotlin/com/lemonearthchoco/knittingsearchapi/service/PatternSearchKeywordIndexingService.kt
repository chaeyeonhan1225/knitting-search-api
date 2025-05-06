package com.lemonearthchoco.knittingsearchapi.service

import com.lemonearthchoco.knittingsearchapi.domain.searchkeyword.SearchKeyword
import com.lemonearthchoco.knittingsearchapi.domain.searchkeyword.SearchKeywordRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PatternSearchKeywordIndexingService(
    val searchKeywordRepository: SearchKeywordRepository
) {
    fun indexing(keyword: String, searchedAt: LocalDateTime) {
        val searchKeyword = SearchKeyword(keyword = keyword, searchedAt = searchedAt)
        searchKeywordRepository.save(searchKeyword)
    }
}