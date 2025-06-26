package lifeful.todo.application

import lifefule.todo.domain.Todo

/**
 * 할일 등록 명령
 */
data class TodoAddCommand(
    val title: String,
    val tasks: List<TaskAddCommand>
) {
    fun toDomain(): Todo {
        return Todo(
            title = title,
            tasks = mutableListOf()
        )
    }
}

/**
 * 태스크 등록 명령
 */
data class TaskAddCommand(
    val note: String,
    val level: String,
    val isCompleted: Boolean = false
) 