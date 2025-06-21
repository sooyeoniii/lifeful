package lifeful.todo.application

import lifefule.todo.domain.Todo

interface FindTodo {
    fun all(): List<Todo>
}