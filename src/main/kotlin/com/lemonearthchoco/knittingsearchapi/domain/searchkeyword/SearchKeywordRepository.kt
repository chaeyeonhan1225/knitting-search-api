package com.lemonearthchoco.knittingsearchapi.domain.searchkeyword

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface SearchKeywordRepository: ElasticsearchRepository<SearchKeyword, String> {
}