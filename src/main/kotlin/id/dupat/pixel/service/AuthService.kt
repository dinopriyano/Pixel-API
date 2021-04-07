package id.dupat.pixel.service

import id.dupat.pixel.model.auth.LoginRequest
import id.dupat.pixel.model.auth.LoginResponse
import id.dupat.pixel.model.auth.RegisterResponse
import id.dupat.pixel.model.user.CreateUserRequest
import id.dupat.pixel.model.user.UserResponse
import org.springframework.web.multipart.MultipartFile

interface AuthService {

    fun login(loginRequest: LoginRequest) : LoginResponse

    fun register(photo: MultipartFile?, createUserRequest: CreateUserRequest): RegisterResponse

}