package lifeful.todo.application

import lifeful.todo.JdslRepository
import lifefule.shared.TaskId
import lifefule.shared.TodoId
import lifefule.todo.domain.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SaveTodoService(
    private val jdslRepository: JdslRepository,
    private val todoRepository: TodoRepository,
    private val todoClient: TodoCheckClient
) : AddTodo, UpdateTodo, DeleteTodo {
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

    override fun addTodo(request: TodoAddCommand) {
        if (request.tasks.isNotEmpty()) {
            addTodoWithTasks(request)
        } else {
            todoRepository.addTodo(request.toDomain())
        }
    }

    override fun addTask(todoId: TodoId, request: List<TaskAddCommand>) {
        //to찾기
        val todo = todoRepository.findById(todoId) ?: throw ClassNotFoundException("todo 없음")
        request.map { taskCommand ->
            val taskEntity = taskCommand.toDomain(todo)
            //init
            todo.saveTask(taskEntity)
            todoRepository.addTask(taskEntity)
        }

        //allcompleted, deleted 변했을 수 도 있어서...다시저장
        todoRepository.addTodo(todo)

    }

    fun add(todoId: TodoId, task: Task) {

        // todo id로 task 리스트를 조회한 후 사이즈 검증
        todoRepository.addTask(task)
    }


    override fun deleteTodo(todoId: TodoId) {
        val todo = todoRepository.findById(todoId)
        return todoRepository.delete(todo)
    }

    override fun updateTodo(todoId: TodoId, request: TodoAddCommand) {
        //jdsl로 find
        todoRepository.findById(todoId) ?: throw ClassNotFoundException("todo 없음")

        //찾아온 todo를 request로 변경
        todoRepository.updateTodo(request.toDomainOnlyTodo(todoId))

    }

    override fun updateTask(todoId: TodoId, taskId: TaskId, request: List<TaskAddCommand>) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val MAX_TASK_SIZE = 10
    }
}