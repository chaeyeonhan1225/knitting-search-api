package com.lemonearthchoco.knittingsearchapi.controller

import com.lemonearthchoco.knittingsearchapi.domain.KnittingObject
import com.lemonearthchoco.knittingsearchapi.service.KnittingObjectProvider
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/knittings")
class KnittingObjectController(
    private val knittingObjectProvider: KnittingObjectProvider
) {
    @GetMapping("/search")
    fun search(@RequestParam("keyword") keyword: String): List<KnittingObject> {
        return knittingObjectProvider.findByFilter(keyword)
    }
}