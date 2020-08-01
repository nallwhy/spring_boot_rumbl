package ee.maytr.rumbl.web.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {
    @GetMapping("/health")
    fun health(): Boolean {
        return true
    }
}
