package lifeful.api

import lifefule.todo.domain.InvalidTodoDataException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = ex.bindingResult.fieldErrors.map { fieldError ->
            "${fieldError.field}: ${fieldError.defaultMessage}"
        }

        val errorResponse = ErrorResponse.of(
            code = ErrorCode.VALIDATION_ERROR.code,
            message = ErrorCode.VALIDATION_ERROR.message,
            details = errors
        )

        return ResponseEntity.status(ErrorCode.VALIDATION_ERROR.httpStatus).body(errorResponse)
    }

    @ExceptionHandler(InvalidTodoDataException::class)
    fun handleInvalidTodoDataException(ex: InvalidTodoDataException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse.of(
            code = ErrorCode.INVALID_ARGUMENT.code,
            message = ex.message ?: ErrorCode.INVALID_ARGUMENT.message
        )

        return ResponseEntity.status(ErrorCode.INVALID_ARGUMENT.httpStatus).body(errorResponse)
    }
}