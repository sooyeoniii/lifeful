package lifefule.todo.domain

import jakarta.persistence.*
import lifefule.shared.BaseEntity
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
        val id : TodoId = TodoId(),
        val title :String,
        @OneToMany(mappedBy = "todo", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
        val tasks: List<Task> = emptyList()
) : BaseEntity()