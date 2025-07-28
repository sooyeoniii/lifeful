package lifeful.api.todo

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import lifeful.todo.application.TaskAddCommand
import lifeful.todo.application.TodoAddCommand
import lifefule.shared.TaskId
import lifefule.shared.TodoId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Tag(
    name = "할일 API",
    description = "할일 API",
)
interface TodoApi {

    @Operation(
        summary = "todo, task 전체 조회",
        operationId = "getTodos",
    )
    fun getTodos(): List<TodoResponse>

    @Operation(
        summary = "todo, task 등록",
        operationId = "addTodo",
    )
    fun addAllTodo(
        @RequestBody request: TodoAddRequest,
    ): ResponseEntity<Unit>

    @Operation(
        summary = "todo 삭제(hard)",
        operationId = "deleteTodo",
    )
    fun deleteTodo(
        @RequestBody todoId: TodoId,
    ): ResponseEntity<Unit>

    @Operation(
        summary = "todo 등록",
        operationId = "addTodo(todo만 저장)",
    )
    fun addTodo(
        @RequestBody request: TodoAddCommand,
    ): ResponseEntity<Unit>

    @Operation(
        summary = "task 등록",
        operationId = "addTodo",
    )
    fun addTask(
        @PathVariable todoId: TodoId,
        @RequestBody request: List<TaskAddCommand>,
    ): ResponseEntity<Unit>

    @Operation(
        summary = "todo 수정",
        operationId = "updateTodo",
    )
    fun updateTodo(
        @PathVariable todoId: TodoId,
        @RequestBody request: TodoAddCommand,
    ): ResponseEntity<Unit>

    @Operation(
        summary = "task 수정",
        operationId = "updateTask",
    )
    fun updateTask(
        @PathVariable todoId: TodoId,
        @PathVariable taskId: TaskId,
        @RequestBody request: List<TaskAddCommand>,
    ): ResponseEntity<Unit>

}
