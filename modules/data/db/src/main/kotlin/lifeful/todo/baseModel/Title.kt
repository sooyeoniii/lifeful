package lifeful.todo.baseModel

import java.util.regex.Pattern

@JvmInline
value class Title(
    val title: String,
) {
    init {
        validate(title)
    }

    private fun validate(name: String) {
        val matcher = DEFAULT_PATTERN.matcher(name)
        if (!matcher.matches()) {
            throw IllegalStateException("제목은 숫자와 특수 문자 입력이 불가능 합니다. - $name")
        }
    }

    companion object {
        private val DEFAULT_PATTERN: Pattern = Pattern.compile("^[a-zA-Z가-힣, ]*$")
    }
}
