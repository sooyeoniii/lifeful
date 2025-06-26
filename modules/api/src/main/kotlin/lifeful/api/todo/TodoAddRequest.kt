package lifeful.api.todo

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import lifefule.todo.domain.Todo
import com.fasterxml.jackson.annotation.JsonProperty

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
        @field:NotBlank(message = "제목은 필수")
        val title: String,
        @get:JsonProperty("isCompleted")
        var isCompleted: Boolean,
        @get:JsonProperty("isDeleted")
        var isDeleted: Boolean,
        val tasks: List<TaskAddRequest>
) {
    fun toDomain(): Todo {
        return Todo(
                title = title,
                isCompleted = isCompleted,
                isDeleted = isDeleted,
                tasks = mutableListOf()
        )
    }
}
