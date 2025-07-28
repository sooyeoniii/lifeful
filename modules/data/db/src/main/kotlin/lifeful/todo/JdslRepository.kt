package lifeful.todo

import com.linecorp.kotlinjdsl.querymodel.jpql.JpqlQueryable
import com.linecorp.kotlinjdsl.querymodel.jpql.delete.DeleteQuery
import com.linecorp.kotlinjdsl.querymodel.jpql.select.SelectQuery
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.support.spring.data.jpa.extension.createQuery
import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutorImpl
import jakarta.persistence.EntityManager
import jakarta.persistence.TypedQuery
import lifeful.todo.baseModel.hibernate.CurrentFunNameHolder
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import kotlin.math.max

@Component
class JdslRepository(
    private val jdslJpqlExecutor: KotlinJdslJpqlExecutorImpl,
    private val entityManager: EntityManager,
    private val jpqlRenderContext: JpqlRenderContext,
) {
    fun <T : Any> findOne(init: DemoJpql.() -> JpqlQueryable<SelectQuery<T>>): T? {
        CurrentFunNameHolder.funName = Thread.currentThread().caller
        return entityManager.createQuery(init(DemoJpql()).toQuery(), jpqlRenderContext)
            .apply { maxResults = 1 }
            .resultList
            .firstOrNull()
    }

    fun <T : Any> findAll(
        offset: Int?,
        limit: Int?,
        init: DemoJpql.() -> JpqlQueryable<SelectQuery<T>>,
    ): List<T> {
        CurrentFunNameHolder.funName = Thread.currentThread().caller
        return jdslJpqlExecutor.findAll(DemoJpql, offset = offset, limit = limit, init)
            .filterNotNull()
    }

    fun <T : Any> findAll(init: DemoJpql.() -> JpqlQueryable<SelectQuery<T>>): List<T> {
        CurrentFunNameHolder.funName = Thread.currentThread().caller
        return jdslJpqlExecutor.findAll(DemoJpql, offset = null, limit = null, init)
            .filterNotNull()
    }

    fun <T : Any> findPage(
        pageable: Pageable,
        init: DemoJpql.() -> JpqlQueryable<SelectQuery<T>>,
    ): Page<T> {
        CurrentFunNameHolder.funName = Thread.currentThread().caller
        val page = jdslJpqlExecutor.findPage(DemoJpql, pageable, init)
        return PageImpl(page.filterNotNull(), pageable, page.totalElements)
    }

    fun <T : Any> delete(init: DemoJpql.() -> JpqlQueryable<DeleteQuery<T>>) {
        CurrentFunNameHolder.funName = Thread.currentThread().caller
        jdslJpqlExecutor.delete(DemoJpql, init)
    }

    private val Thread.caller: String
        get() {
            val stack = this.stackTrace[3]
            return "${stack.className}-${stack.methodName}"
        }
}