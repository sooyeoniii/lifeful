package lifeful.todo.baseModel.serializer

import com.linecorp.kotlinjdsl.querymodel.jpql.expression.Expression
import com.linecorp.kotlinjdsl.querymodel.jpql.predicate.Predicate
import lifeful.todo.baseModel.Title

internal data class DemoJpqlNameLike internal constructor(
    val value: Expression<Title>,
    val pattern: Expression<String>,
) : Predicate
