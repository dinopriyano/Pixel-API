package id.dupat.pixel.service.impl

import id.dupat.pixel.entity.User
import id.dupat.pixel.exception.NotFoundException
import id.dupat.pixel.model.auth.LoginRequest
import id.dupat.pixel.model.auth.LoginResponse
import id.dupat.pixel.repository.UserRepository
import id.dupat.pixel.security.JWTAuthorizationProvider
import id.dupat.pixel.service.AuthService
import id.dupat.pixel.util.ValidationUtil
import id.dupat.pixel.util.getJWTToken
import id.dupat.pixel.util.toLoginResponse
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(val userRepository: UserRepository, val validation: ValidationUtil, val jwtAuthorizationProvider: JWTAuthorizationProvider): AuthService {

    override fun login(loginRequest: LoginRequest): LoginResponse {
        validation.validate(loginRequest)
        val user = findUserOrThrow(loginRequest)
        val token = jwtAuthorizationProvider.createToken(user.email)
        return user.toLoginResponse(token)
    }

    private fun findUserOrThrow(loginRequest: LoginRequest): User {
        val user = userRepository.findByEmailAndPassword(loginRequest.email!!,loginRequest.password!!)
        if(user == null){
            throw NotFoundException()
        }

        return user
    }

}