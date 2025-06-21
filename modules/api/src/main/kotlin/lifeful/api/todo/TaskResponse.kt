package lifeful.api.todo

import io.swagger.v3.oas.annotations.media.Schema
import lifefule.todo.domain.Task
import java.time.LocalDateTime

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
@Schema(description = "할일 태스크 응답")
data class TaskResponse(
        val id: Int,
        val note: String,
        val level: String,
        val isCompleted: Boolean,
        val createdAt: LocalDateTime,
        val modifiedAt: LocalDateTime
) {
        companion object {
                fun from(task: Task): TaskResponse {
                        return TaskResponse(
                                id = task.id,
                                note = task.note,
                                level = task.level,
                                isCompleted = task.isCompleted,
                                createdAt = task.createdAt,
                                modifiedAt = task.modifiedAt
                        )
                }
        }
}