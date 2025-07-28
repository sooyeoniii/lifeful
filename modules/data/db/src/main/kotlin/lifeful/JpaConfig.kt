package lifeful

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(
    basePackages = [
        "lifeful.book.data",
        "lifeful.review.data",
        "lifeful.todo.data"
    ],
)
@EntityScan(
    basePackages = [
        "lifefule.book.domain",
        "lifefule.review.domain",
        "lifefule.todo.domain",
    ],
)
@Configuration(proxyBeanMethods = false)
class JpaConfig
