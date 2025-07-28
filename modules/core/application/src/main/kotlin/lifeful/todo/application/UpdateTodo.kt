package lifeful.todo.application

import lifefule.shared.TaskId
import lifefule.shared.TodoId

/**
 * lifeful.todo.application.UpdateTodo
 * <p>
 * UpdateTodo
 *
 * @author 메가존 양수연
 * @version 1.0
 * @since 2025/07/21
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일        수정자           수정내용
 *  ----------    --------        ---------------------------
 *  2025/07/21    양수연            최초 생성
 * </pre>
 */
interface UpdateTodo {

    fun updateTodo(todoId: TodoId, request: TodoAddCommand)
    fun updateTask(todoId: TodoId, taskId: TaskId, request: List<TaskAddCommand>)
}