package lifeful.todo.application

import lifefule.shared.TodoId
import lifefule.todo.domain.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class TodoService(
        private val todoRepository : TodoRepository,
        private val todoClient: TodoCheckClient
):AddTodo {
    override fun add(todo: Todo): TodoId {
        //task도 저장한다고 여길 두번 타서 if 추가
        if(todo.id.value == 0){
            //검증
            todo.validate()
            //require("false".equals(todoClient.isClean(todo.title))) {"비속어불가"}
            //true:비속어O
            todo.isClean("false" == todoClient.isProfane(todo.title))
            todo.maxSize()
        }

        todoRepository.addTodo(todo)
        return todo.id
    }

    fun add(task: Task) {
       todoRepository.addTask(task)
    }

    override fun deleteTodo(todoId: TodoId): Unit {
        val todo = todoRepository.findById(todoId)
        todoRepository.delete(todo)
        return Unit
    }


}