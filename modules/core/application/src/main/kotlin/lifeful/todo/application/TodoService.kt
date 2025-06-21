package lifeful.todo.application

import lifefule.shared.TodoId
import lifefule.todo.domain.InvalidTodoDataException
import lifefule.todo.domain.Todo
import lifefule.todo.domain.TodoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class TodoService(
        private val todoRepository : TodoRepository,
):AddTodo {
    override fun add(todo: Todo): TodoId {
        // 비즈니스 로직 검증
        if (todo.title.isBlank()) {
            throw InvalidTodoDataException()
        }

//        https://www.purgomalum.com/service/containsprofanity?text=


        todoRepository.addTodo(todo)
        return todo.id
    }
    
    @Transactional(readOnly = true)
    fun getTodos(): List<Todo> {
        return todoRepository.findAll()
    }
}