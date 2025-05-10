package com.lemonearthchoco.knittingsearchapi.domain.pattern

import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface PatternRepository: ElasticsearchRepository<Pattern, String> {
    @Query(
        """
            {
              "bool": {
                "must": [
                  { "match": { "name": "?0" }},
                  { "match": { "designer": "?0" }}
                ]
              }
            }
        """
    )
    fun findPatternsByKeyword(keyword: String): List<Pattern>
}