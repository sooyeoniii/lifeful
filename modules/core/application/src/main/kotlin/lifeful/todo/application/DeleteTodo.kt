package lifeful.todo.application

import lifefule.shared.TodoId

/**
 * lifeful.todo.application.DeleteTodo
 * <p>
 * DeleteTodo
 *
 * @author 메가존 양수연
 * @version 1.0
 * @since 2025/06/26
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일        수정자           수정내용
 *  ----------    --------        ---------------------------
 *  2025/06/26    양수연            최초 생성
 * </pre>
 */
interface DeleteTodo {
    fun deleteTodo(todoId: TodoId)

}