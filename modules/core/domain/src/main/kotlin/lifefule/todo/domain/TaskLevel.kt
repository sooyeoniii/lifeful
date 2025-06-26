package lifefule.todo.domain

/**
 * <p>
 * TaskLevel
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
enum class TaskLevel(val value: String, val displayName: String) {
    HIGH("HIGH", "높음"),
    MEDIUM("MEDIUM", "보통"),
    LOW("LOW", "낮음");

    companion object {
        fun from(value: String): TaskLevel {
            return entries.find { it.value == value.uppercase() }
                    ?: throw IllegalArgumentException("유효하지 않은 TaskLevel: $value")
        }

        fun isValid(value: String): Boolean {
            return values().any { it.value == value.uppercase() }
        }
    }
} 