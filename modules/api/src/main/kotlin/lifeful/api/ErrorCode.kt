package lifeful.api

import org.springframework.http.HttpStatus

enum class ErrorCode(
        val httpStatus: HttpStatus,
        val code: String,
        val message: String,

        ) {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "400", "입력값 검증에 실패했습니다"),

    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST, "400", "잘못된 인자가 전달되었습니다"),

    PROFANITY_DETECTED(HttpStatus.BAD_REQUEST, "1000", "비속어 사용 불가"),

    OVER_MAX(HttpStatus.BAD_REQUEST, "2000", "TASK는 최대 10개 등록 가능합니다."),
}