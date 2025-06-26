package lifeful.api.todo

import jakarta.validation.Valid
import lifeful.todo.application.TodoAddCommand
import lifeful.todo.application.TaskAddCommand
import lifeful.todo.application.TodoContentService
import lifeful.todo.application.TodoService
import lifefule.shared.TodoId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RequestMapping("/v1/todo")
@RestController
class TodoRestController(
        private val todoService: TodoService,
        private val findTodo: TodoContentService
) : TodoApi {
    @GetMapping("/todos")
    override fun getTodos(): List<TodoResponse> {
        val todos = findTodo.all()
        return todos.map { TodoResponse.from(it) }
    }

    @PostMapping("/todo")
    override fun addTodo(@Valid @RequestBody request: TodoAddRequest): ResponseEntity<Unit> {
        val command = TodoAddCommand(
                title = request.title,
                tasks = request.tasks.map { taskRequest ->
                    TaskAddCommand(
                            note = taskRequest.note,
                            level = taskRequest.level,
                            isCompleted = taskRequest.isCompleted
                    )
                }
        )
        val id = todoService.addTodoWithTasks(command)
        val location = URI.create("/todo/${id.value}")
        return ResponseEntity.created(location).build()
    }

    @PostMapping("/delete/{todoId}")
    override fun deleteTodo(@PathVariable todoId: TodoId): ResponseEntity<Unit> {
        todoService.deleteTodo(todoId)
        return ResponseEntity.ok().build()
    }

}
