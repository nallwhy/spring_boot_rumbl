package ee.maytr.rumbl.web.channel

import mu.KotlinLogging
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Mono

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
}
