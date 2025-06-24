package lifeful.todo.data

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import lifefule.shared.TaskId
import lifefule.todo.domain.Task
import org.springframework.data.jpa.repository.JpaRepository

internal interface TaskJpaRepository : JpaRepository<Task, TaskId>, KotlinJdslJpqlExecutor {
}