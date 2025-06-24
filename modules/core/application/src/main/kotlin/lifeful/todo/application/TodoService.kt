package lifeful.todo.application

import lifefule.shared.TodoId
import lifefule.todo.domain.Task
import lifefule.todo.domain.Todo
import lifefule.todo.domain.TodoRepository
import lifefule.todo.domain.validate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class TodoService(
        private val todoRepository : TodoRepository,
):AddTodo {
    override fun add(todo: Todo): TodoId {
        // 검증
        todo.validate()

        todoRepository.addTodo(todo)
        return todo.id
    }

    fun add(task: Task) {
       todoRepository.addTask(task)
    }
    
    @Transactional(readOnly = true)
    fun getTodos(): List<Todo> {
        return todoRepository.findAll()
    }

}