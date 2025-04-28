package com.lemonearthchoco.knittingsearchapi.service

import com.lemonearthchoco.knittingsearchapi.domain.KnittingObject
import com.lemonearthchoco.knittingsearchapi.domain.KnittingObjectRepository
import org.springframework.stereotype.Service

@Service
class KnittingObjectProvider(
    private val repository: KnittingObjectRepository
) {
    fun findByFilter(keyword: String): List<KnittingObject> {
        return repository.findAll().toList()
    }
}