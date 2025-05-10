package com.lemonearthchoco.knittingsearchapi.domain.searchkeyword

import org.springframework.data.domain.Page
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.core.SearchHit
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import java.awt.print.Pageable

interface SearchKeywordRepository: ElasticsearchRepository<SearchKeyword, String> {
    @Query(
        """
            {
                "bool": {
                    "must_not": {
                        "exists": { "field": "patternIndexedAt" }
                    }
                }
            }
        """
    )
    fun findSearchKeywordsIfNullPatternIndexedAt(): List<SearchKeyword>
}
