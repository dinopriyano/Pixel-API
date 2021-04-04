package id.dupat.pixel.service.impl

import id.dupat.pixel.entity.User
import id.dupat.pixel.model.CreateUserRequest
import id.dupat.pixel.model.UserResponse
import id.dupat.pixel.repository.UserRepository
import id.dupat.pixel.service.UserService
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(val userRepository: UserRepository) : UserService {
    override fun create(createUserRequest: CreateUserRequest): UserResponse {
        val user = User(
            id = createUserRequest.id!!,
            name = createUserRequest.name!!,
            email = createUserRequest.email!!,
            password = createUserRequest.password!!,
            gender = createUserRequest.gender!!,
            phone = createUserRequest.phone!!,
            created_at = Date(),
            updated_at = null
        )

        userRepository.save(user)

        return UserResponse(
            id = user.id!!,
            name = user.name!!,
            email = user.email!!,
            password = user.password!!,
            gender = user.gender!!,
            phone = user.phone!!,
            created_at = user.created_at,
            updated_at = null
        )

    }
}