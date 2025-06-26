package lifeful.api.todo

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import lifefule.todo.domain.Todo
import java.time.LocalDateTime

@Schema(description = "Todo 응답")
data class TodoResponse(
        @field:Schema(description = "Todo 식별")
        val id: Int,

        @field:Schema(description = "제목")
        val title: String,

        @field:Schema(description = "완료여부")
        val allCompleted: Boolean,

        @field:Schema(description = "삭제여부")
        val allDeleted: Boolean,

        @field:Schema(description = "작성 날짜")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val createdAt: LocalDateTime,

        @field:Schema(description = "수정 날짜")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val modifiedAt: LocalDateTime,

        @field:Schema(description = "tasks")
        val tasks: List<TaskResponse>
) {
    companion object {
        fun from(todo: Todo): TodoResponse {
            return TodoResponse(
                    id = todo.id.value,
                    title = todo.title,
                    //task의 isCompl이 전부 true여야 true
                    //리소스 ,,과부하,,,,db에잇어야댐(통계)
                    allCompleted = todo.tasks.all { it.isCompleted },
                allDeleted = todo.allDeleted,
                    createdAt = todo.createdAt,
                    modifiedAt = todo.modifiedAt,
                    tasks = todo.tasks.map { TaskResponse.from(it) }
            )
        }
    }
}