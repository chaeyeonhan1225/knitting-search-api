package com.lemonearthchoco.knittingsearchapi.domain.searchkeyword

import org.springframework.cglib.core.Local
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDateTime

@Document(indexName = "search_keyword")
data class SearchKeyword(
    @Id
    val keyword: String, // keyword
    @Field(type = FieldType.Date, format = [DateFormat.date_hour_minute_second_millis])
    val searchedAt: LocalDateTime,
    @Field(type = FieldType.Date, format = [DateFormat.date_hour_minute_second_millis])
    var patternIndexedAt: LocalDateTime? = null
) {
    fun setPatternIndexed() {
        patternIndexedAt = LocalDateTime.now()
    }
}
