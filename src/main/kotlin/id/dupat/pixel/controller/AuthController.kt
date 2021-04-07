package id.dupat.pixel.controller

import id.dupat.pixel.model.WebResponse
import id.dupat.pixel.model.auth.LoginRequest
import id.dupat.pixel.model.auth.LoginResponse
import id.dupat.pixel.model.auth.RegisterResponse
import id.dupat.pixel.model.user.CreateUserRequest
import id.dupat.pixel.model.user.UserResponse
import id.dupat.pixel.service.AuthService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
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

    @PostMapping(
        value = ["/api/auth/register"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    fun register(@RequestParam("photo") photo: MultipartFile?, body: CreateUserRequest): WebResponse<RegisterResponse>{
        val response = authService.register(photo, body)
        return WebResponse(
            code = 200,
            error = false,
            message = "Register success",
            data = response
        )
    }

}