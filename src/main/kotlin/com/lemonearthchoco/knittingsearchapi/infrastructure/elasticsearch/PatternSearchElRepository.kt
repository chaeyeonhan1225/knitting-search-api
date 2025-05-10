package com.lemonearthchoco.knittingsearchapi.infrastructure.elasticsearch

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders
import com.lemonearthchoco.knittingsearchapi.domain.pattern.Pattern
import com.lemonearthchoco.knittingsearchapi.domain.pattern.PatternSearchRepository
import org.springframework.data.elasticsearch.client.elc.NativeQuery
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.stereotype.Repository

@Repository
class PatternSearchElRepository(
    private val elOperations: ElasticsearchOperations
): PatternSearchRepository {
    override fun search(keyword: String, page: Int, pageSize: Int): List<Pattern> {
        val boolQuery = QueryBuilders.bool {
            it.should { s -> s.match { m -> m.field("name").query(keyword) }}
            it.should { s -> s.match { m -> m.field("designer").query(keyword) }}
            it.should { s -> s.match { m -> m.field("detail").query(keyword) }}
//            it.should { s -> s.nested { n -> n.path("needles").query { q ->
//                q.match { m -> m.field("name").query(keyword) }
//            }}}
        }

        val query = NativeQuery.builder().withQuery(boolQuery).build()
        return elOperations.search(query, Pattern::class.java).searchHits.map { it.content }
    }
}