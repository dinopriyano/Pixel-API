package id.dupat.pixel.service.impl

import id.dupat.pixel.config.WebSecurityConfig
import id.dupat.pixel.entity.User
import id.dupat.pixel.exception.CustomException
import id.dupat.pixel.exception.NotFoundException
import id.dupat.pixel.model.auth.LoginRequest
import id.dupat.pixel.model.auth.LoginResponse
import id.dupat.pixel.model.auth.RegisterResponse
import id.dupat.pixel.model.user.CreateUserRequest
import id.dupat.pixel.model.user.UserResponse
import id.dupat.pixel.repository.UserRepository
import id.dupat.pixel.security.JWTAuthorizationProvider
import id.dupat.pixel.service.AuthService
import id.dupat.pixel.service.FileService
import id.dupat.pixel.util.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class AuthServiceImpl(val validationUtil: ValidationUtil,val fileService: FileService, val userRepository: UserRepository, val validation: ValidationUtil, val jwtAuthorizationProvider: JWTAuthorizationProvider, val webSecurityConfig: WebSecurityConfig): AuthService {

    override fun login(loginRequest: LoginRequest): LoginResponse {
        validation.validate(loginRequest)
        val user = findUserOrThrow(loginRequest)
        val token = jwtAuthorizationProvider.createToken(user.email)
        return user.toLoginResponse(token)
    }

    override fun register(photo: MultipartFile?, createUserRequest: CreateUserRequest): RegisterResponse {
        validationUtil.validate(createUserRequest)
        isUserExist(createUserRequest.email!!)

        val photo = if(photo != null){fileService.uploadFile(photo)} else {null}
        val user = User(
            id = createUserRequest.id!!,
            name = createUserRequest.name!!,
            email = createUserRequest.email!!,
            password = webSecurityConfig.passwordEncoder().encode(createUserRequest.password!!),
            gender = createUserRequest.gender!!,
            phone = createUserRequest.phone!!,
            photo = photo,
            created_at = Date(),
            updated_at = null
        )

        userRepository.save(user)

        return user.toRegisterResponse()
    }

    private fun findUserOrThrow(loginRequest: LoginRequest): User {
        val user = userRepository.findByEmail(loginRequest.email!!)
        if(user == null){
            throw NotFoundException()
        }
        else if(!webSecurityConfig.passwordEncoder().matches(loginRequest.password, user.password)){
            throw CustomException("Wrong password")
        }

        return user
    }

    private fun isUserExist(email: String){
        val user = userRepository.findByEmail(email)
        if(user != null){
            throw CustomException("This email already used")
        }
    }

    private fun findUserOrThrow(id: String): User{
        val user = userRepository.findByIdOrNull(id)
        if(user == null){
            throw NotFoundException()
        }

        return user
    }

}