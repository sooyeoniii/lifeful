package lifefule.todo.domain

/**
 * lifefule.todo.domain.TodoCheckClient
 * <p>
 * TodoCheckClient
 *
 * @author 메가존 양수연
 * @version 1.0
 * @since 2025/06/25
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일        수정자           수정내용
 *  ----------    --------        ---------------------------
 *  2025/06/25    양수연            최초 생성
 * </pre>
 */
interface TodoCheckClient {

    fun isProfane(text: String): Boolean
}