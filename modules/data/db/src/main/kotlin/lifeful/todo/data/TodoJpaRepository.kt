package lifeful.todo.data

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import lifefule.shared.TodoId
import lifefule.todo.domain.Todo
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

internal interface TodoJpaRepository : JpaRepository<Todo, TodoId>, KotlinJdslJpqlExecutor {
    
    @EntityGraph(attributePaths = ["tasks"])
    override fun findAll(): List<Todo>
    
    @EntityGraph(attributePaths = ["tasks"])
    override fun findById(id: TodoId): java.util.Optional<Todo>
}