package lifeful.todo.client

import lifefule.todo.domain.TodoCheckClient
import org.springframework.stereotype.Component

/**
 * lifeful.todo.client.TodoClientImpl
 * <p>
 * TodoClientImpl
 *
 * @author 메가존 양수연
 * @version 1.0
 * @since 2025/06/26
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일        수정자           수정내용
 *  ----------    --------        ---------------------------
 *  2025/06/26    양수연            최초 생성
 * </pre>
 */
@Component
class TodoClientImpl(
        private val feignClient: TodoFeignClient,
) : TodoCheckClient {
    override fun isProfane(text: String): Boolean {
        return feignClient.isProfane(text).toBoolean()
    }
}