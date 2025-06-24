package lifeful

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LifefulApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<LifefulApplication>(*args)
        }
    }
}
