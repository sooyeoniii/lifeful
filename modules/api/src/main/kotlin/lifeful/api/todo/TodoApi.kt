package lifeful.api.todo

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

@Tag(
        name = "할일 API",
        description = "할일 API",
)
interface TodoApi {

    @Operation(
            summary = "할일 목록 조회",
            operationId = "getTodos",
    )
    fun getTodos(): List<TodoResponse>

    @Operation(
            summary = "할일 등록",
            operationId = "addTodo",
    )
    fun addTodo(
            @RequestBody request: TodoAddRequest,
    ): ResponseEntity<Unit>

}
