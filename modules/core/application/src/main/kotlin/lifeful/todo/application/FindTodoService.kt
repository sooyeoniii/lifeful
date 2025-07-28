package lifeful.todo.application

import lifefule.todo.domain.Todo
import lifefule.todo.domain.TodoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = false)
@Service
class FindTodoService(
    private val todoRepository: TodoRepository
) : FindTodo {
    override fun all(): List<Todo> {

//        if(todoRepository.findAll().isEmpty()){
//            throw InvalidTodoDataException()
//        }
        return todoRepository.findAll()
    }

}