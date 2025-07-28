package lifeful.todo.baseModel.serializer

import com.linecorp.kotlinjdsl.querymodel.jpql.expression.Expression
import com.linecorp.kotlinjdsl.querymodel.jpql.predicate.Predicate
import lifeful.todo.baseModel.TodoNo

internal data class DemoJpqlDmsNoLike internal constructor(
    val value: Expression<TodoNo>,
    val pattern: Expression<String>,
) : Predicate
