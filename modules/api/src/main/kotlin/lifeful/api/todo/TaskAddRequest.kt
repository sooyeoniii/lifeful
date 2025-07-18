package lifeful.api.todo

import io.swagger.v3.oas.annotations.media.Schema
import lifefule.shared.TodoId

/**
 * lifeful.api.todo.TaskAddRequest
 * <p>
 * TaskAddRequest
 *
 * @author 메가존 양수연
 * @version 1.0
 * @since 2025/06/20
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일        수정자           수정내용
 *  ----------    --------        ---------------------------
 *  2025/06/20    양수연            최초 생성
 * </pre>
 */
@Schema(description = "할일 태스크 등록")
data class TaskAddRequest(
    val todoId: TodoId,
    val note: String,
    val level: String,
    val isCompleted: Boolean = false,
    val isDeleted: Boolean = false
)