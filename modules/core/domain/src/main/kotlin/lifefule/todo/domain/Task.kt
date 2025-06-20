package lifefule.todo.domain

import jakarta.persistence.*
import lifefule.shared.BaseEntity

/**
 * lifefule.todo.domain.Task
 * <p>
 * Task
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
class Task(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id :Int,
        val note :String,
        val level :String,
        val isCompleted : Boolean,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "todo_id")
        val todo: Todo
) : BaseEntity()