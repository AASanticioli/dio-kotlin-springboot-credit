package me.dio.credit.exception

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails> {
        val errors: MutableMap<String, String> = HashMap()
        ex.bindingResult.allErrors.stream().forEach { err: ObjectError ->
            val fieldName: String = (err as FieldError).field
            val message: String = err.defaultMessage.orEmpty()
            errors[fieldName] = message
        }
        val details = ExceptionDetails(
            title = "Invalid Arguments",
            moment = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            exception = ex.javaClass.simpleName.toString(),
            details = errors
        )
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(details)
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun methodArgumentTypeMismatchExceptionHandler(ex: MethodArgumentTypeMismatchException): ResponseEntity<ExceptionDetails> {
        val errorCause: String = ex.cause?.toString() ?: "Cause"
        val errorMessage: String = ex.message?:"Undefined"
        val errors: MutableMap<String, String> = mutableMapOf(errorCause to errorMessage)
        val details = ExceptionDetails(
            title = "Type Mismatch Argument",
            moment = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            exception = ex.javaClass.simpleName.toString(),
            details = errors
        )
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(details)
    }

    @ExceptionHandler(DataAccessException::class)
    fun dataAccessExceptionHandler(ex: DataAccessException): ResponseEntity<ExceptionDetails> {
        val errors: MutableMap<String, String> = mutableMapOf(ex.cause.toString() to ex.message.orEmpty())
        val details = ExceptionDetails(
            title = "Constraint Violation",
            moment = LocalDateTime.now(),
            status = HttpStatus.CONFLICT.value(),
            exception = ex.javaClass.simpleName.toString(),
            details = errors
        )
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(details)
    }

    @ExceptionHandler(BusinessException::class)
    fun businessExceptionHandler(ex: BusinessException): ResponseEntity<ExceptionDetails> {
        val errorCause: String = ex.cause?.toString() ?: "Cause"
        val errorMessage: String = ex.message
        val errors: MutableMap<String, String> = mutableMapOf(errorCause to errorMessage)
        val status: HttpStatus =HttpStatus.BAD_REQUEST
        val details = ExceptionDetails(
            title = "Business Exception",
            moment = LocalDateTime.now(),
            status = status.value(),
            exception = ex.javaClass.simpleName.toString(),
            details = errors
        )
        return ResponseEntity
            .status(status)
            .body(details)
    }

}