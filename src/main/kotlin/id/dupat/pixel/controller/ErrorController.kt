package id.dupat.pixel.controller

import id.dupat.pixel.exception.NotFoundException
import id.dupat.pixel.model.UserResponse
import id.dupat.pixel.model.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): WebResponse<UserResponse?>{
        return WebResponse(
            code = 400,
            error = true,
            message = constraintViolationException.message,
            data = null
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundHandler(notFoundException: NotFoundException): WebResponse<UserResponse?>{
        return WebResponse(
            code = 404,
            error = true,
            message = "Data not found",
            data = null
        )
    }
}