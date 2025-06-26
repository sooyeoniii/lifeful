package lifefule.todo.domain

import lifefule.shared.TodoId

interface TodoRepository {

    fun addTodo(todo: Todo)

    fun addTask(task: Task)

    fun findAll(): List<Todo>
    
    fun findById(id: TodoId): Todo?

    fun delete(todo: Todo?)
}