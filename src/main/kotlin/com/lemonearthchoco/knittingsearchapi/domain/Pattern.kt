package com.lemonearthchoco.knittingsearchapi.domain

import org.springframework.data.elasticsearch.annotations.Document
import java.math.BigDecimal
import java.util.*

@Document(indexName = "pattern")
data class Pattern(
    val id: String,
    val name: String,
    val imageUrl: String? = null,
    val designer: String,
    val detail: String? = null,
    val needles: List<Needle> = listOf(),
    val gauges: List<String> = listOf(),
    val price: Money = Money.free,
    val yarn: Yarn? = null,
    val languages: List<String> = listOf()
)

data class Needle(
    val id: String,
    val name: String
)

data class Yarn(
    val yardage: Yardage,
    val weight: String
)

data class Yardage(
    val min: Int,
    val max: Int
)

data class Money(
    val value: BigDecimal,
    val currency: String
) {
    companion object {
        val free = Money(BigDecimal.ZERO, "USD")
    }
}