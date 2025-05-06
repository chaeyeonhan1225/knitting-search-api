package com.lemonearthchoco.knittingsearchapi.service

import com.lemonearthchoco.knittingsearchapi.domain.pattern.Pattern

interface PatternSearchUseCase {
    fun findPatterns(query: String, page: Int, pageSize: Int): List<Pattern>
}