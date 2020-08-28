package ee.maytr.rumbl.web.controller

import ee.maytr.rumbl.domain.service.HealthService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(HealthController::class)
class HealthControllerTests {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var healthService: HealthService

    @Test
    fun `health returns "healthy"`() {
        `when`(healthService.get()).thenReturn("healthy")

        mockMvc.perform(get("/health"))
            .andExpect(status().isOk)
            .andExpect(content().string("healthy"))
    }
}
