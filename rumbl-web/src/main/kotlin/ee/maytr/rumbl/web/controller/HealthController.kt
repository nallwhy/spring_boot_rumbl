package ee.maytr.rumbl.web.controller

import ee.maytr.rumbl.domain.service.HealthService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController(
    private val healthService: HealthService
) {
    @GetMapping("/health")
    fun health(): String {
        return healthService.get()
    }
}
