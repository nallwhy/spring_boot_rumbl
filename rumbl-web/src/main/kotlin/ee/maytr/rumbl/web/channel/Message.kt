package ee.maytr.rumbl.web.channel

import java.time.Instant

data class Message(
    val content: String,
    val createdAt: Long = Instant.now().toEpochMilli()
)
