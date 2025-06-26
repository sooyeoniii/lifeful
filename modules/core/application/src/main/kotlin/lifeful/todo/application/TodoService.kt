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
    //command->request
    override fun addTodoWithTasks(todo: TodoAddCommand): TodoId {
        if (todoClient.isProfane(todo.title)) {
            throw ProfanityDetectedException()
        }
        // 1. todo 생성 및 검증
        val todos = todo.toDomain()
        //task도 저장한다고 여길 두번 타서 if 추가

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
        if (tasks.size != MAX_TASK_SIZE) {
            // 예외
        }

        // 4. 다시 저장ㅠ,,(tasks 포함)
        todoRepository.addTodo(todos)

        return todos.id
    }

    fun add(todoId: TodoId, task: Task) {

        // todo id로 task 리스트를 조회한 후 사이즈 검증
        todoRepository.addTask(task)
    }

    override fun deleteTodo(todoId: TodoId) {
        val todo = todoRepository.findById(todoId)
        return todoRepository.delete(todo)
    }

    companion object {
        private const val MAX_TASK_SIZE = 10
    }
}