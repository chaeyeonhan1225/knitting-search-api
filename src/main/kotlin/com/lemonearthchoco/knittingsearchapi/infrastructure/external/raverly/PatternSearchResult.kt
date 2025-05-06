package com.lemonearthchoco.knittingsearchapi.infrastructure.external.raverly

import com.fasterxml.jackson.annotation.JsonProperty
import com.lemonearthchoco.knittingsearchapi.domain.*
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

/**
 *  {
 *                     "id": 122126114,
 *                     "primary_pack_id": null,
 *                     "project_id": null,
 *                     "skeins": null,
 *                     "stash_id": null,
 *                     "total_grams": null,
 *                     "total_meters": null,
 *                     "total_ounces": null,
 *                     "total_yards": null,
 *                     "yarn_id": 205948,
 *                     "yarn_name": "Noro Madara",
 *                     "yarn_weight": {
 *                         "crochet_gauge": "",
 *                         "id": 1,
 *                         "knit_gauge": "18",
 *                         "max_gauge": null,
 *                         "min_gauge": null,
 *                         "name": "Aran",
 *                         "ply": "10",
 *                         "wpi": "8"
 *                     },
 *                     "colorway": null,
 *                     "shop_name": null,
 *                     "yarn": {
 *                         "permalink": "noro-madara",
 *                         "id": 205948,
 *                         "name": "Madara",
 *                         "yarn_company_name": "Noro",
 *                         "yarn_company_id": 15
 *                     },
 *                     "quantity_description": null,
 *                     "personal_name": null,
 *                     "dye_lot": null,
 *                     "color_family_id": null,
 *                     "grams_per_skein": null,
 *                     "yards_per_skein": null,
 *                     "meters_per_skein": null,
 *                     "ounces_per_skein": null,
 *                     "prefer_metric_weight": true,
 *                     "prefer_metric_length": false,
 *                     "shop_id": null,
 *                     "thread_size": null
 *                 }
 */
data class PatternResult(
    val id: String,
    val name: String,
    @JsonProperty("pattern_author")
    val patternAuthor: PatternDesigner,
    @JsonProperty("comments_count")
    val commentsCount: Int,
    @JsonProperty("created_at")
    val createdAt: String,
    val price: BigDecimal,
    val currency: String,
    @JsonProperty("difficulty_average")
    val difficultyAverage: BigDecimal,
    @JsonProperty("difficulty_count")
    val difficultyCount: Int,
    @JsonProperty("favorites_count")
    val favoritesCount: Int,
    val free: Boolean,
    val yardage: Int,
    @JsonProperty("yardage_max")
    val yardageMax: Int,
    @JsonProperty("yarn_weight_description")
    val yarnWeightDescription: String,
    val gauge: BigDecimal,
    @JsonProperty("gauge_divisor")
    val gaugeDivisor: Int,
    @JsonProperty("gauge_pattern")
    val gaugePattern: String,
    val notes: String,
    val published: String,
    @JsonProperty("rating_average")
    val ratingAverage: BigDecimal,
    @JsonProperty("rating_count")
    val ratingCount: Int,
    @JsonProperty("pattern_needle_size")
    val patternNeedleSize: List<NeedleDetail>?
) {
    fun toDomain() = Pattern(
        id = "raverly:$id",
        name = name,
        designer = patternAuthor.name,
        detail = notes,
        needles = patternNeedleSize?.map {
            Needle(
                id = "raverly:needles:$id",
                name = name
            )
        } ?: emptyList(),
        gauges = listOf(),
        price = when (free) {
            true -> Money.free
            false -> Money(
                price,
                currency
            )
        },
        yarn = Yarn(
            yardage = Yardage(
                yardage,
                yardageMax
            ),
            weight = yarnWeightDescription
        ),
        languages = listOf()
    )
}

/**
 *  {
 *                     "id": 6,
 *                     "us": "6 ",
 *                     "metric": 4.0,
 *                     "us_steel": null,
 *                     "crochet": false,
 *                     "knitting": true,
 *                     "hook": "G",
 *                     "name": "US 6  - 4.0 mm",
 *                     "pretty_metric": "4"
 *                 },
 */
data class NeedleDetail(
    val id: Long,
    val name: String,
    val us: String,
    val metric: BigDecimal,
)