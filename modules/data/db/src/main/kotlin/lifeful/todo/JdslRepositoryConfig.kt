package lifeful.todo

import com.linecorp.kotlinjdsl.render.RenderContext
import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutorImpl
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * lifeful.jdsl.JdslRepositoryConfig
 * <p>
 * JdslRepositoryConfig
 *
 * @author 메가존 양수연
 * @version 1.0
 * @since 2025/07/21
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일        수정자           수정내용
 *  ----------    --------        ---------------------------
 *  2025/07/21    양수연            최초 생성
 * </pre>
 */
@Configuration(proxyBeanMethods = false)
internal class JdslRepositoryConfig(
    private val entityManager: EntityManager,
    private val renderContexts: List<RenderContext>,
) {
    @Bean
    fun jdslJpqlExecutor(): KotlinJdslJpqlExecutorImpl {
        val renderContext = renderContexts.reversed().reduce { acc, renderContext -> acc + renderContext }
        return KotlinJdslJpqlExecutorImpl(entityManager, renderContext, null)
    }
}