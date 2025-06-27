package lifeful.api.todo

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import lifefule.todo.domain.Todo

/**
 * lifeful.api.todo.AddTodoWithoutTaskRequest
 * <p>
 * AddTodoWithoutTaskRequest
 *
 * @author 메가존 양수연
 * @version 1.0
 * @since 2025/06/27
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일        수정자           수정내용
 *  ----------    --------        ---------------------------
 *  2025/06/27    양수연            최초 생성
 * </pre>
 */
@Schema(description = "todo 등록")
data class AddTodoWithoutTaskRequest(
        @field:NotBlank(message = "제목은 필수")
        val title: String,
        val allCompleted: Boolean,
        val allDeleted: Boolean,
        val tasks: List<TaskAddRequest>
) {
    fun toDomain(): Todo {
        return Todo(
                title = title,
                allCompleted = allCompleted,
                allDeleted = allDeleted,
                tasks = mutableListOf()
        )
    }
}

