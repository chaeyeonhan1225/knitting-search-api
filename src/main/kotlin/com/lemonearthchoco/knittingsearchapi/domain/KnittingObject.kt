package com.lemonearthchoco.knittingsearchapi.domain

import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "knitting_object")
data class KnittingObject(
    val id: String?,
    val title: String,
    val pattern: Pattern?
)