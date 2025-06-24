package lifeful.todo.data

import lifefule.todo.domain.Todo
import lifefule.todo.domain.TodoRepository
import lifefule.shared.TodoId
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class TodoRdbRepository(
        //feignclient도 지금 이 repository처럼 받아서 함
        private val jpaRepository : TodoJpaRepository
) : TodoRepository {
    @Transactional
    override fun addTodo(todo: Todo) {
        jpaRepository.save(todo)
    }
    
    @Transactional(readOnly = true)
    override fun findAll(): List<Todo> {
        return jpaRepository.findAll()
    }
    
    @Transactional(readOnly = true)
    override fun findById(id: TodoId): Todo? {
        return jpaRepository.findById(id).orElse(null)
    }
}