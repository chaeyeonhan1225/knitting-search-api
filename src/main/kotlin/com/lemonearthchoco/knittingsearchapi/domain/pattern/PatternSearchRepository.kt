package com.lemonearthchoco.knittingsearchapi.domain.pattern

interface PatternSearchRepository {
    fun search(query: String, page: Int, pageSize: Int): List<Pattern>
}