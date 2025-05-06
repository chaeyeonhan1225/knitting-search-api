package com.lemonearthchoco.knittingsearchapi.domain.pattern

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface PatternRepository: ElasticsearchRepository<Pattern, String>