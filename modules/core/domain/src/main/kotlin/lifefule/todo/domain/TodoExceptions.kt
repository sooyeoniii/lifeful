package lifefule.todo.domain

class InvalidTodoDataException() : RuntimeException()
class ProfanityDetectedException() : RuntimeException()
class TooManyDataException() : RuntimeException()

fun Todo.validate() {
    //require:false면 예외던짐
    require(title.isNotBlank()) { throw InvalidTodoDataException() }
}

fun Todo.isClean(text: Boolean) {
    require(text) { throw ProfanityDetectedException() }
}

fun Todo.maxSize() {
    println("=----------" + tasks.size)
    require(tasks.size <= 10) { throw TooManyDataException() }
}

