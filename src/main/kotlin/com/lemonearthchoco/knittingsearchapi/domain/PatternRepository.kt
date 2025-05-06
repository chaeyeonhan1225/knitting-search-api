package com.lemonearthchoco.knittingsearchapi.domain

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface PatternRepository: ElasticsearchRepository<Pattern, String>