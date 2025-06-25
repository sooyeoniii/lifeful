package lifeful.api.todo

import jakarta.validation.Valid
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

    @PostMapping("/todos")
    override fun addTodo(
            @Valid @RequestBody request: TodoAddRequest): ResponseEntity<Unit> {
        // 1. todo 객체 생성 (tasks 없이)
        val todo = request.toDomain()
        // 2. Todo id 생성
        todoService.add(todo)
        // 3. Task 리스트 생성 (todo를 할당)
        val tasks = request.tasks.map { it.toDomain(todo) }
        // 4. Todo에 tasks 할당
        todo.tasks = tasks.toMutableList()
        // 5. 다시 저장ㅠ,..
        todoService.add(todo)
        val location = URI.create("/todo/${todo.id.value}")
        return ResponseEntity.created(location).build()
    }

    @PostMapping("/delete/{todoId}")
    override fun deleteTodo(@PathVariable todoId: TodoId): ResponseEntity<Unit> {
        todoService.deleteTodo(todoId)
        return ResponseEntity.ok().build()

    }

}
