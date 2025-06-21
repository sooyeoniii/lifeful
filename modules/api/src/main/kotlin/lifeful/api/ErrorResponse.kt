package lifeful.api

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "에러 응답")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ErrorResponse(
    @field:Schema(description = "에러 코드")
    val code: String,

    @field:Schema(description = "에러 메시지")
    val message: String,

    @field:Schema(description = "발생 시간")
    val timestamp: LocalDateTime = LocalDateTime.now(),

    @field:Schema(description = "상세 에러 정보")
    val details: List<String>? = null
) {
    companion object {
        fun of(code: String, message: String, details: List<String>? = null): ErrorResponse {
            return ErrorResponse(code = code, message = message, details = details)
        }
    }
}