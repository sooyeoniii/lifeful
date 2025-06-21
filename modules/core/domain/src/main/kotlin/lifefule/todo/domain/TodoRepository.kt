package lifefule.todo.domain

import lifefule.shared.TodoId

interface TodoRepository {

    fun addTodo(todo: Todo)
    
    fun findAll(): List<Todo>
    
    fun findById(id: TodoId): Todo?
}