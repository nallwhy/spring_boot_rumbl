package ee.maytr.rumbl.web.channel

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.rsocket.context.LocalRSocketServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.messaging.rsocket.RSocketRequester
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.Duration

@SpringBootTest
class HealthChannelTests {
    companion object {
        lateinit var requester: RSocketRequester

        @BeforeAll
        @JvmStatic
        internal fun beforeAll(
            @Autowired builder: RSocketRequester.Builder,
            @LocalRSocketServerPort port: Int
        ) {
            requester = builder.tcp("localhost", port)
        }
    }

    @Test
    fun `request-response model`() {
        val response: Mono<Message> =
            requester
                .route("request-response")
                .data(Message("test"))
                .retrieveMono(Message::class.java)

        StepVerifier
            .create(response)
            .consumeNextWith { message ->
                assertEquals("You said: test", message.content)
            }
            .verifyComplete()
    }

    @Test
    fun `fire-and-forget model`() {
        val response: Mono<Void> =
            requester
                .route("fire-and-forget")
                .data(Message("test"))
                .retrieveMono(Void::class.java)

        StepVerifier
            .create(response)
            .verifyComplete()
    }

    @Test
    fun `request-stream model`() {
        val stream: Flux<Message> =
            requester
                .route("request-stream")
                .data(Message("test"))
                .retrieveFlux(Message::class.java)

        StepVerifier
            .create(stream)
            .consumeNextWith { message ->
                assertEquals("You said: test. Response #0", message.content)
            }
            .consumeNextWith { message ->
                assertEquals("You said: test. Response #1", message.content)
            }
            .thenCancel()
            .verify()
    }

    @Test
    fun `stream-stream model`() {
        val setting0: Mono<Long> = Mono.just(2L).delayElement(Duration.ofSeconds(0))
        val setting1: Mono<Long> = Mono.just(1L).delayElement(Duration.ofSeconds(5))

        val settings: Flux<Long> = Flux.concat(setting0, setting1)

        val stream: Flux<Message> =
            requester
                .route("stream-stream")
                .data(settings)
                .retrieveFlux(Message::class.java)

        StepVerifier
            .create(stream)
            .consumeNextWith { message ->
                assertEquals("Stream response #0", message.content)
            }
            .consumeNextWith { message ->
                assertEquals("Stream response #1", message.content)
            }
            .consumeNextWith { message ->
                assertEquals("Stream response #0", message.content)
            }
            .consumeNextWith { message ->
                assertEquals("Stream response #1", message.content)
            }
            .thenCancel()
            .verify()
    }
}
