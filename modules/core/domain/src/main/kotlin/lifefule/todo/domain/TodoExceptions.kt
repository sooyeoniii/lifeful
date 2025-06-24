package lifefule.todo.domain

class InvalidTodoDataException() : RuntimeException()

fun Todo.validate() {
    require(title.isNotBlank()) { throw InvalidTodoDataException()}
}