package lifeful.api

import lifefule.todo.domain.InvalidTodoDataException
import lifefule.todo.domain.ProfanityDetectedException
import lifefule.todo.domain.TooManyDataException
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
                message = ErrorCode.INVALID_ARGUMENT.message
        )

        return ResponseEntity.status(ErrorCode.INVALID_ARGUMENT.httpStatus).body(errorResponse)
    }

    @ExceptionHandler(ProfanityDetectedException::class)
    fun handleInvalidTodoDataException(ex: ProfanityDetectedException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse.of(
                code = ErrorCode.PROFANITY_DETECTED.code,
                message = ErrorCode.PROFANITY_DETECTED.message
        )

        return ResponseEntity.status(ErrorCode.PROFANITY_DETECTED.httpStatus).body(errorResponse)
    }

    @ExceptionHandler(TooManyDataException::class)
    fun handleInvalidTodoDataException(ex: TooManyDataException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse.of(
                code = ErrorCode.OVER_MAX.code,
                message = ErrorCode.OVER_MAX.message
        )

        return ResponseEntity.status(ErrorCode.OVER_MAX.httpStatus).body(errorResponse)
    }
}