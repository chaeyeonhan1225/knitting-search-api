package com.lemonearthchoco.knittingsearchapi.worker

import com.lemonearthchoco.knittingsearchapi.service.IndexingService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class PatternIndexingWorker(
    private val indexingService: IndexingService
) {
    @Scheduled(fixedRate = 5 * 60 * 1000)  // 5분 마다 실행
    fun indexingPatterns() {
        indexingService.indexingPatternsBulk()
    }
}