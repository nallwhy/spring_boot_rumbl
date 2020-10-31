package ee.maytr.rumbl.web.channel

import mu.KotlinLogging
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@Controller
class HealthChannel {
    companion object {
        private val logger = KotlinLogging.logger {}
    }

    @MessageMapping("request-response")
    fun requestResponse(message: Message): Mono<Message> {
        logger.debug { "request-response message: $message" }
        return Mono.just(Message("You said: ${message.content}"))
    }

    @MessageMapping("fire-and-forget")
    fun fireAndForget(message: Message): Mono<Void> {
        logger.debug { "fire-and-forget message: $message" }
        return Mono.empty()
    }

    @MessageMapping("request-stream")
    fun requestStream(message: Message): Flux<Message> {
        logger.debug { "request-stream message: $message" }
        return Flux
            .interval(Duration.ofSeconds(1))
            .map { i ->
                Message("You said: ${message.content}. Response #$i")
            }
            .log()
    }
}
