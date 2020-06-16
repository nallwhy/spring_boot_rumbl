package ee.maytr.rumbl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RumblApplication

fun main(args: Array<String>) {
	runApplication<RumblApplication>(*args)
}
