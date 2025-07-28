package lifeful.todo

import com.linecorp.kotlinjdsl.dsl.jpql.Jpql
import com.linecorp.kotlinjdsl.dsl.jpql.JpqlDsl
import com.linecorp.kotlinjdsl.querymodel.jpql.expression.Expressions
import com.linecorp.kotlinjdsl.querymodel.jpql.path.Path
import com.linecorp.kotlinjdsl.querymodel.jpql.predicate.Predicate
import com.linecorp.kotlinjdsl.querymodel.jpql.predicate.Predicates
import lifeful.todo.baseModel.Title
import lifeful.todo.baseModel.TodoNo
import lifeful.todo.baseModel.serializer.DemoJpqlDmsNoLike
import lifeful.todo.baseModel.serializer.DemoJpqlNameLike

class DemoJpql : Jpql() {
    fun Path<Title>.like(value: String): Predicate {
        return DemoJpqlNameLike(this.toExpression(), Expressions.value(value))
    }

    fun <T : TodoNo> Path<T>.startsWith(no: T): Predicate {
        return DemoJpqlDmsNoLike(this.toExpression(), Expressions.value("${no.value}%"))
    }

    fun Path<String>.startsWith(value: String): Predicate {
        return Predicates.like(this.toExpression(), Expressions.value("$value%"))
    }

    fun Path<String>.endsWith(value: String): Predicate {
        return Predicates.like(this.toExpression(), Expressions.value("%$value"))
    }

    fun Path<String>.contains(value: String): Predicate {
        return Predicates.like(this.toExpression(), Expressions.value("%$value%"))
    }

    fun Path<String>.regexLike(pattern: String): Predicate {
        return Predicates.function("REGEXP_LIKE", listOf(this.toExpression(), Expressions.value(pattern)))
            .toPredicate()
    }

    // TODO: 성능 문제 개선 필요
    fun Path<String>.startsWithKorean(): Predicate {
        return Predicates.function("REGEXP_LIKE", listOf(this.toExpression(), Expressions.value(KOREAN_REGEX)))
            .toPredicate()
    }

    // TODO: 성능 문제 개선 필요
    fun Path<String>.startsWithSpecialChars(): Predicate {
        return Predicates.function("REGEXP_LIKE", listOf(this.toExpression(), Expressions.value(SPECIAL_CHARACTERS_REGEX)))
            .toPredicate()
    }

    // TODO: 성능 문제 개선 필요
    fun Path<String>.startsWithAlphabet(): Predicate {
        return Predicates.function("REGEXP_LIKE", listOf(this.toExpression(), Expressions.value(ALPHABET_REGEX)))
            .toPredicate()
    }

    companion object Constructor : JpqlDsl.Constructor<DemoJpql> {
        private const val SPECIAL_CHARACTERS_REGEX = "^[^가-힣a-zA-Z0-9]"
        private const val KOREAN_REGEX = "^[가-힣]"
        private const val ALPHABET_REGEX = "^[a-zA-Z]"

        override fun newInstance(): DemoJpql = DemoJpql()
    }
}