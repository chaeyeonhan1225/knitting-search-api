package com.lemonearthchoco.knittingsearchapi.handler

import com.lemonearthchoco.knittingsearchapi.domain.event.PatternSearched
import com.lemonearthchoco.knittingsearchapi.service.PatternSearchKeywordIndexingService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class PatternSearchedEventHandler(
    private val indexingService: PatternSearchKeywordIndexingService
) {
    @Async
    @EventListener
    fun handlePatternSearchedEvent(event: PatternSearched) {
        indexingService.indexing(event.keyword, event.searchedAt)
    }
}