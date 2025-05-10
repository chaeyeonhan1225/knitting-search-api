package com.lemonearthchoco.knittingsearchapi.infrastructure.external.raverly

import com.fasterxml.jackson.annotation.JsonProperty
import com.lemonearthchoco.knittingsearchapi.domain.pattern.*
import java.math.BigDecimal

data class PatternSearchResult(
    val patterns: List<PatternListingResult>?
)

data class PatternListingResult(
    val id: Long,
    val name: String,
    val permalink: String,
    val designer: PatternDesigner,
) {
    fun toDomain(): Pattern = Pattern(
        id = "raverly:$id",
        name = name,
        designer = designer.name
    )
}

data class PatternDesigner(
    val id: Long,
    val name: String,
    val patterns_count: Int,
    val knitting_pattern_count: Int,
    val crochet_pattern_count: Int
)

data class PatternDetailSearchResult(
    val patterns: Map<String, PatternResult>
)

data class PatternResult(
    val id: String,
    val name: String,
    @JsonProperty("pattern_author")
    val patternAuthor: PatternDesigner,
    @JsonProperty("comments_count")
    val commentsCount: Int,
    @JsonProperty("created_at")
    val createdAt: String,
    val price: BigDecimal?,
    val currency: String?,
    @JsonProperty("difficulty_average")
    val difficultyAverage: BigDecimal,
    @JsonProperty("difficulty_count")
    val difficultyCount: Int,
    @JsonProperty("favorites_count")
    val favoritesCount: Int,
    val free: Boolean,
    val photos: List<PatternPhoto>,

    val packs: List<Pack>,
    val yardage: Int?,
    @JsonProperty("yardage_max")
    val yardageMax: Int?,
    @JsonProperty("yarn_weight_description")
    val yarnWeightDescription: String?,
    val gauge: BigDecimal?,
    @JsonProperty("row_gauge")
    val rowGauge: BigDecimal?,
    @JsonProperty("gauge_divisor")
    val gaugeDivisor: Int?,
    @JsonProperty("gauge_pattern")
    val gaugePattern: String?,
    @JsonProperty("gauge_description")
    val gaugeDescription: String?,
    val notes: String,
    val published: String?,
    @JsonProperty("rating_average")
    val ratingAverage: BigDecimal,
    @JsonProperty("rating_count")
    val ratingCount: Int,
    @JsonProperty("pattern_needle_sizes")
    val patternNeedleSizes: List<NeedleDetail>?,
    val languages: List<PatternSupportedLauguage>
) {
    fun toDomain() = Pattern(
        id = "raverly:$id",
        name = name,
        designer = patternAuthor.name,
        imageUrl = photos.firstOrNull()?.imageUrl,
//        detail = notes,
        detail = "",
        needles = patternNeedleSizes?.map {
            Needle(
                id = "raverly:needles:${it.id}",
                name = it.name,
                metric = it.metric
            )
        } ?: emptyList(),
        gauges = GaugeDetail(
            gauge = Gauge(
                stitches = gauge,
                rows = rowGauge,
                divisor = gaugeDivisor ?: 1,
            ),
            pattern = gaugePattern,
            description = gaugeDescription
        ),
        price = when (free) {
            true -> Money.free
            false -> Money(
                price ?: BigDecimal.ZERO,
                currency ?: "USD"
            )
        },
        yarns = YarnDetail(
            yarns = packs.map {
                Yarn(
                    id = "raverly:yarn:${it.id}",
                    name = it.name ?: "unknown",
                    crochetGauge = it.yarnWeight?.crochetGauge,
                    knitGauge = it.yarnWeight?.knitGauge,
                    ply = it.yarnWeight?.ply,
                    wpi = it.yarnWeight?.wpi,
                )
            },
            yardage = Yardage(yardage ?: 0, yardageMax ?: 0),
            weight = yarnWeightDescription ?: ""
        ),
        languages = languages.map { it.code }
    )
}

data class Pack(
    val id: Long,
    @JsonProperty("yarn_name")
    val name: String?,
    @JsonProperty("yarn_weight")
    val yarnWeight: YarnWeight?
)

data class YarnWeight(
    val crochetGauge: String?,
    val knitGauge: String?,
    val name: String?,
    val ply: String?,
    val wpi: String?
)

data class NeedleDetail(
    val id: Long,
    val name: String,
    val us: String?,
    val metric: BigDecimal,
)

data class PatternPhoto(
    @JsonProperty("square_url")
    val imageUrl: String
)

data class PatternSupportedLauguage(
    val code: String
)