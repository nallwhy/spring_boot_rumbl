package ee.maytr.rumbl.domain

import ee.maytr.rumbl.domain.service.HealthService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HealthServiceTest(
    @Autowired private val healthService: HealthService
) {
    @Test
    fun contextLoads() {
        assertThat(healthService.get()).isEqualTo("healthy")
    }

    @SpringBootApplication
    internal class TestConfiguration
}
