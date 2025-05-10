package com.lemonearthchoco.knittingsearchapi.domain.searchkeyword

import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

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

    fun findSearchKeywordsByKeywordAndPatternIndexedAtNull(keyword: String): SearchKeyword?
}
