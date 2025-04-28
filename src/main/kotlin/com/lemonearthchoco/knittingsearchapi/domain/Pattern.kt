package com.lemonearthchoco.knittingsearchapi.domain

import org.springframework.data.elasticsearch.annotations.Document

data class Pattern(
    val author: String,
    val title: String,
    val category: String
)