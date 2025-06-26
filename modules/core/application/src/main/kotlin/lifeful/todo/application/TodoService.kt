package lifeful.todo.application

import lifefule.shared.TodoId
import lifefule.todo.domain.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoService(
        private val todoRepository: TodoRepository,
        private val todoClient: TodoCheckClient
) : AddTodo, DeleteTodo {
    @Transactional
    override fun add(todo: Todo): TodoId {
        todoRepository.addTodo(todo)
        return todo.id
    }

    @Transactional
    override fun addTodoWithTasks(todo: TodoAddCommand): TodoId {
        // 1. todo 생성 및 검증
        val todos = todo.toDomain()
        //task도 저장한다고 여길 두번 타서 if 추가
        if (todos.id.value == 0) {
            todos.validate()
            //require("false".equals(todoClient.isClean(todo.title))) {"비속어불가"}
            //true:비속어O
            todos.isClean("false" == todoClient.isProfane(todo.title))
        }

        // 2. todo 저장 (id땜에)
        todoRepository.addTodo(todos)

        // 3. task 생성
        val tasks = todo.tasks.map { taskCommand ->
            Task(
                    note = taskCommand.note,
                    level = taskCommand.level,
                    isCompleted = taskCommand.isCompleted,
                    todo = todos
            )
        }

        todos.tasks = tasks.toMutableList()
        //task가 들어오는시점,,
        todos.maxSize()

        // 4. 다시 저장ㅠ,,(tasks 포함)
        todoRepository.addTodo(todos)

        return todos.id
    }


    fun add(task: Task) {
        todoRepository.addTask(task)
    }

    override fun deleteTodo(todoId: TodoId) {
        val todo = todoRepository.findById(todoId)
        return todoRepository.delete(todo)
    }

}