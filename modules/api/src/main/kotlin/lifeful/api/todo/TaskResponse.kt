package lifeful.api.todo

import io.swagger.v3.oas.annotations.media.Schema

/**
 * lifeful.api.todo.TaskResponse
 * <p>
 * TaskResponse
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
@Schema(description = "task 응답")
data class TaskResponse (
        @field:Schema(description = "task 식별")
        val id: Int,

        @field:Schema(description = "작업내용")
        val note: String,

        @field:Schema(description = "우선순위")
        val level: String,

        @field:Schema(description = "완료여부")
        val isCompleted: Boolean,

) {
}