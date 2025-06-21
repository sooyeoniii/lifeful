package lifeful.api.todo

import jakarta.validation.Valid
import lifeful.todo.application.TodoContentService
import lifeful.todo.application.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RequestMapping("/todo/v1")
@RestController
class TodoRestController(
        private val todoService: TodoService,
        private val findTodo: TodoContentService
) : TodoApi {
    @GetMapping("/list")
    override fun getTodos(): List<TodoResponse> {
//        val todos = todoService.getTodos()
//        return todos.map { TodoResponse.from(it) }
        val todos = findTodo.all()
        return todos.map { TodoResponse.from(it)}
    }

    @PostMapping("/add")
    override fun addTodo(
            @Valid @RequestBody request: TodoAddRequest): ResponseEntity<Unit> {
        val todo = request.toDomain()
        todoService.add(todo)
        val location = URI.create("/todo/${todo.id.value}")

        return ResponseEntity.created(location).build()
    }

}
