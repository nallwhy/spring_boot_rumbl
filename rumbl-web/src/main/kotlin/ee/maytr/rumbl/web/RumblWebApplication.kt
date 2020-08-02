package ee.maytr.rumbl.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["ee.maytr.rumbl.domain", "ee.maytr.rumbl.web"])
class RumblWebApplication

fun main(args: Array<String>) {
    runApplication<RumblWebApplication>(*args)
}
