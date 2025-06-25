package lifefule.todo.domain

class InvalidTodoDataException() : RuntimeException()

fun Todo.validate() {
    //false면 예외던짐
    require(title.isNotBlank()) { throw InvalidTodoDataException()}
}

fun Todo.isClean(text: Boolean) {
    require(text) {"비속어 불가"}
}

fun Todo.maxSize() {
    require(tasks.size <= 10) {"최대 10개까지 등록 가능"}
}

