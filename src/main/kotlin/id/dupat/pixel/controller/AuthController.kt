package id.dupat.pixel.controller

import id.dupat.pixel.model.WebResponse
import id.dupat.pixel.model.auth.LoginRequest
import id.dupat.pixel.model.auth.LoginResponse
import id.dupat.pixel.service.AuthService
import org.springframework.web.bind.annotation.*
import java.security.GeneralSecurityException
import java.text.ParseException
import kotlin.jvm.Throws

@RestController
class AuthController(val authService: AuthService) {

    @PostMapping(
        value = ["/api/auth/login"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    fun login(body: LoginRequest): WebResponse<LoginResponse> {
        val response = authService.login(body)
        return WebResponse(
            code = 200,
            error = false,
            message = "Login success",
            data = response
        )
    }

}