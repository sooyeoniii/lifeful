package lifeful.api.todo

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import lifefule.shared.TodoId
import org.springframework.http.ResponseEntity
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
    fun addTodo(
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
    fun addTodoWithoutTodo(
            @RequestBody request: TodoAddRequest,
    ): ResponseEntity<Unit>

    @Operation(
            summary = "task 등록",
            operationId = "addTodo",
    )
    fun addTask(
            @RequestBody request: TodoAddRequest,
    ): ResponseEntity<Unit>

}
