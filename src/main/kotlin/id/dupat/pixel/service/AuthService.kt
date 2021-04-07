package id.dupat.pixel.service

import id.dupat.pixel.model.auth.LoginRequest
import id.dupat.pixel.model.auth.LoginResponse

interface AuthService {

    fun login(loginRequest: LoginRequest) : LoginResponse

}