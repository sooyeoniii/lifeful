package lifeful.api

enum class ErrorCode(
    val code: String,
    val message: String,
    val httpStatus: Int
) {
    // 검증 에러
    VALIDATION_ERROR("VALIDATION_ERROR", "입력값 검증에 실패했습니다", 400),

    // 인자 에러
    INVALID_ARGUMENT("INVALID_ARGUMENT", "잘못된 인자가 전달되었습니다", 400),

    // 리소스 에러
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "요청한 리소스를 찾을 수 없습니다", 404),

    // 비즈니스 로직 에러
    TODO_NOT_FOUND("TODO_NOT_FOUND", "할일을 찾을 수 없습니다", 404),
    TASK_NOT_FOUND("TASK_NOT_FOUND", "태스크를 찾을 수 없습니다", 404),
}