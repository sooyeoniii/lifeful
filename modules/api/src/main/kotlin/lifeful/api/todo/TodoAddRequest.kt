package lifeful.api.todo

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size
import lifefule.todo.domain.Task

/**
 * lifeful.api.todo.TodoAddRequest
 * <p>
 * TodoAddRequest
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
@Schema(description = "할일 등록")
data class TodoAddRequest(
        val id :Int,
        @field:Size(min = 1, message = "제목 빈값 안됨")
        val title :String,
        val tasks: MutableList<Task> = mutableListOf()
)
