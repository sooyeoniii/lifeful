package lifeful.todo.application

import lifefule.shared.TodoId
import lifefule.todo.domain.Todo

interface AddTodo {

    /**
     * 할일 등록
     */
    fun add(todo: Todo) : TodoId
    fun deleteTodo(todoId: TodoId): Unit

}