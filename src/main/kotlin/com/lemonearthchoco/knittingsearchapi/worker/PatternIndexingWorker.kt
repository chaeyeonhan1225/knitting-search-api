package com.lemonearthchoco.knittingsearchapi.worker

import com.lemonearthchoco.knittingsearchapi.service.PatternIndexingService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class PatternIndexingWorker(
    private val patternIndexingService: PatternIndexingService
) {
    @Scheduled(fixedRate = 1 * 60 * 1000)  // 5분 마다 실행
    fun indexingPatterns() {
        patternIndexingService.indexingPatternsBulk()
    }
}