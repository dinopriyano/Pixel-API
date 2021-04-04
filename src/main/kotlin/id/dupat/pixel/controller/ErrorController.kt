package id.dupat.pixel.controller

import id.dupat.pixel.exception.CustomException
import id.dupat.pixel.exception.NotFoundException
import id.dupat.pixel.model.user.UserResponse
import id.dupat.pixel.model.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.MaxUploadSizeExceededException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
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

    @ExceptionHandler(value = [CustomException::class])
    fun customExceptionHandler(customException: CustomException): WebResponse<UserResponse?>{
        return WebResponse(
            code = 400,
            error = true,
            message = customException.message,
            data = null
        )
    }

    @ExceptionHandler(MaxUploadSizeExceededException::class)
    fun handleFileUploadException(
        exception: MaxUploadSizeExceededException?,
        httpServletRequest: HttpServletRequest?,
        httpServletResponse: HttpServletResponse?
    ): String? {
        return "File Size limit exceeded. Please make sure the file size is well within 128KB"
    }
}