package ee.maytr.rumbl.web.controller

import ee.maytr.rumbl.domain.service.HealthService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController(
    private val healthService: HealthService
) {
    companion object {
        private val logger = KotlinLogging.logger {}
    }

    @GetMapping("/health")
    fun health(): String {
        logger.debug { "health" }

        return healthService.get()
    }
}
