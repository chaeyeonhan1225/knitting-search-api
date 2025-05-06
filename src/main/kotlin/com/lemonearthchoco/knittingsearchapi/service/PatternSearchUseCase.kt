package com.lemonearthchoco.knittingsearchapi.service

import com.lemonearthchoco.knittingsearchapi.domain.Pattern
import java.awt.print.Pageable

interface PatternSearchUseCase {
    fun findPatterns(query: String, page: Int, pageSize: Int): List<Pattern>
}