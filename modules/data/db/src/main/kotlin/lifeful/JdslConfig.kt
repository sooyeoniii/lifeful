package lifeful

import com.linecorp.kotlinjdsl.querymodel.jpql.expression.impl.JpqlValue
import com.linecorp.kotlinjdsl.render.RenderContext
import com.linecorp.kotlinjdsl.render.jpql.serializer.JpqlSerializer
import com.linecorp.kotlinjdsl.render.jpql.serializer.impl.JpqlValueSerializer
import com.linecorp.kotlinjdsl.render.jpql.writer.JpqlWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Configuration(proxyBeanMethods = false)
class JdslConfig {
    @Bean
    fun jpqlSerializer(): JpqlSerializer<*> {
        return ValueClassAwareJpqlValueSerializer(JpqlValueSerializer())
    }
}

class ValueClassAwareJpqlValueSerializer(
    private val delegate: JpqlValueSerializer,
) : JpqlSerializer<JpqlValue<*>> {
    override fun handledType(): KClass<JpqlValue<*>> {
        return JpqlValue::class
    }

    override fun serialize(
        part: JpqlValue<*>,
        writer: JpqlWriter,
        context: RenderContext,
    ) {
        val value = part.value

        if (value::class.isValue) {
            writer.writeParam(value::class.memberProperties.first().getter.call(value))
            return
        }

        delegate.serialize(part, writer, context)
    }
}
