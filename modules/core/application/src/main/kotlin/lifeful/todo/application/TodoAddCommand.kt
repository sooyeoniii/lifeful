package lifeful.todo.application

import lifefule.shared.TodoId
import lifefule.todo.domain.Task
import lifefule.todo.domain.Todo
import lifefule.todo.domain.TaskLevel

/**
 * 할일 등록 명령
 */
data class TodoAddCommand(
    val title: String,
    val allCompleted: Boolean,
    val allDeleted: Boolean,
    val tasks: List<TaskAddCommand>
) {
    fun toDomain(): Todo {
        return Todo(
            title = title,
            allCompleted = allCompleted,
            allDeleted = allDeleted,
            tasks = mutableListOf()
        )
    }

    fun toDomainOnlyTodo(todoId: TodoId): Todo {
        return Todo(
            id = todoId,
            title = title,
            allCompleted = allCompleted,
            allDeleted = allDeleted
        )
    }
}

/**
 * 태스크 등록 명령
 */
data class TaskAddCommand(
    val note: String,
    val level: TaskLevel,
    val isCompleted: Boolean,
    val isDeleted: Boolean,
) {
    fun toDomain(todo: Todo): Task {
        return Task(
            note = note,
            level = level,
            isCompleted = isCompleted,
            isDeleted = isDeleted,
            todo = todo
        )

    }
}