package com.lemonearthchoco.knittingsearchapi.domain.searchkeyword

import java.time.LocalDateTime

data class SearchKeyword(
    val keyword: String,
    val searchedAt: LocalDateTime,
    val indexedAt: LocalDateTime? = null
)
