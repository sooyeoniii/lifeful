package lifeful.api.todo

import jakarta.validation.Valid
import lifeful.todo.application.TodoAddCommand
import lifeful.todo.application.TaskAddCommand
import lifeful.todo.application.FindTodoService
import lifeful.todo.application.SaveTodoService
import lifefule.shared.TaskId
import lifefule.shared.TodoId
import lifefule.todo.domain.TaskLevel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RequestMapping("/v1/todo")
@RestController
class TodoRestController(
    private val saveTodo: SaveTodoService,
    private val findTodo: FindTodoService
) : TodoApi {
    @GetMapping("/todos")
    override fun getTodos(): List<TodoResponse> {
        val todos = findTodo.all()
        return todos.map { TodoResponse.from(it) }
    }

    @PostMapping("/todos/total")
    override fun addAllTodo(@Valid @RequestBody request: TodoAddRequest): ResponseEntity<Unit> {
        val command = TodoAddCommand(
            title = request.title,
            allCompleted = request.allCompleted,
            allDeleted = request.allDeleted,
            tasks = request.tasks.map { taskRequest ->
                TaskAddCommand(
                    note = taskRequest.note,
                    level = TaskLevel.from(taskRequest.level),
                    isCompleted = taskRequest.isCompleted,
                    isDeleted = taskRequest.isDeleted,
                )
            }
        )
        val id = saveTodo.addTodoWithTasks(command)
        val location = URI.create("/todo/${id.value}")
        return ResponseEntity.created(location).build()
    }

    @PostMapping("/delete/{todoId}")
    override fun deleteTodo(@PathVariable todoId: TodoId): ResponseEntity<Unit> {
        saveTodo.deleteTodo(todoId)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/todos")
    override fun addTodo(request: TodoAddCommand): ResponseEntity<Unit> {
        saveTodo.addTodo(request)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/tasks/{todoId}")
    override fun addTask(@PathVariable todoId: TodoId, request: List<TaskAddCommand>): ResponseEntity<Unit> {
        saveTodo.addTask(todoId, request)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/todo/{todoId}")
    override fun updateTodo(@PathVariable todoId: TodoId, request: TodoAddCommand): ResponseEntity<Unit> {
        saveTodo.updateTodo(todoId, request)
        return ResponseEntity.ok().build()
    }

    override fun updateTask(
        @PathVariable todoId: TodoId,
        @PathVariable taskId: TaskId,
        request: List<TaskAddCommand>
    ): ResponseEntity<Unit> {
        saveTodo.updateTask(todoId, taskId, request)
        return ResponseEntity.ok().build()
    }


}
