package com.lemonearthchoco.knittingsearchapi.domain.pattern

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.elasticsearch.annotations.Document
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "pattern")
data class Pattern(
    val id: String,
    val name: String,
    val imageUrl: String? = null,
    val designer: String,
    val detail: String? = null,
    val needles: List<Needle> = listOf(),
    val gauges: GaugeDetail? = null,
    val price: Money = Money.free,
    val yarns: YarnDetail? = null,
    val languages: List<String> = listOf()
) {
    constructor(): this(
        id = "",
        name= "",
        imageUrl = null,
        designer = ""
    )
}

data class Needle(
    val id: String,
    val name: String,
    val metric: BigDecimal,
)

data class YarnDetail(
    val yarns: List<Yarn>,
    val yardage: Yardage,
    val weight: String
)

data class Yarn(
    val id: String,
    val name: String,
    val crochetGauge: String?,
    val knitGauge: String?,
    val ply: String?,   // 합 e.g. 2합, 5합
    val wpi: String?    // 실 굵기 DK Worsted
)

data class Yardage(
    val min: Int,
    val max: Int
)

data class GaugeDetail(
    val gauge: Gauge?,
    val pattern: String?,
    val description: String?,
)

data class Gauge(
    val stitches: BigDecimal?,
    val rows: BigDecimal?,
    val divisor: Int
)

data class Money(
    val value: BigDecimal,
    val currency: String
) {
    companion object {
        val free = Money(BigDecimal.ZERO, "USD")
    }
}