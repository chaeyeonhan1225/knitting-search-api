package com.lemonearthchoco.knittingsearchapi.domain.event

import java.time.LocalDateTime

data class PatternSearched(
    val keyword: String,
    val searchedAt: LocalDateTime = LocalDateTime.now()
)
