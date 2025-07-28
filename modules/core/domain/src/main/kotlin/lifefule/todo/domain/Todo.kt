package lifefule.todo.domain

import jakarta.persistence.*
import lifefule.shared.BaseEntity
import lifefule.shared.TaskId
import lifefule.shared.TodoId

/**
 * lifefule.todo.domain.Todo
 * <p>
 * Todo
 *
 * @author 메가존 양수연
 * @version 1.0
 * @since 2025/06/20
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일        수정자           수정내용
 *  ----------    --------        ---------------------------
 *  2025/06/20    양수연            최초 생성
 * </pre>
 */
@Entity
class Todo(
    //id는 자동채번이지 디폴트값이 필요
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: TodoId = TodoId(),
    val title: String,
    var allCompleted: Boolean,
    var allDeleted: Boolean,
    @OneToMany(mappedBy = "todo", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var tasks: MutableList<Task> = mutableListOf()
) : BaseEntity() {
    init {
        //throw포함
        require(title.isNotBlank()) { "제목 공백 안돼" }
        require(tasks.size <= 10) { "task는 10개 까지" }
    }

    fun saveTask(task: Task) {
        require(tasks.size < 10)
        tasks.add(task)
        task.todo = this

        if (tasks.all { it.isCompleted }) {
            this.allCompleted = true
        } else {
            this.allCompleted = false
        }
    }

    fun delete() {
        this.allDeleted = true
        tasks.forEach {
            it.delete()
        }
    }

    fun deleteTask(taskId: TaskId) {
        val find = tasks.find { it.id == taskId } ?: throw IllegalArgumentException()
        find.delete()
    }
}