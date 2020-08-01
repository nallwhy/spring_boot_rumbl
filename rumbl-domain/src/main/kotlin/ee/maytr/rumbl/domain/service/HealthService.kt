package ee.maytr.rumbl.domain.service

import org.springframework.stereotype.Service

@Service
class HealthService {
    fun get(): String {
        return "healthy"
    }
}
