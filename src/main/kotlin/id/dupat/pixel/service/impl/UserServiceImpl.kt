package id.dupat.pixel.service.impl

import id.dupat.pixel.entity.User
import id.dupat.pixel.exception.CustomException
import id.dupat.pixel.exception.NotFoundException
import id.dupat.pixel.model.user.*
import id.dupat.pixel.repository.UserRepository
import id.dupat.pixel.service.UserService
import id.dupat.pixel.util.ValidationUtil
import id.dupat.pixel.util.toUserResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class UserServiceImpl(val userRepository: UserRepository, val validationUtil: ValidationUtil) : UserService {
    override fun create(createUserRequest: CreateUserRequest): UserResponse {
        validationUtil.validate(createUserRequest)

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

        return user.toUserResponse()

    }

    override fun getById(id: String): UserResponse {
        val user = findProductOrThrow(id)

        return user.toUserResponse()
    }

    override fun update(id: String, updateUserRequest: UpdateUserRequest): UserResponse {
        val user = findProductOrThrow(id)

        validationUtil.validate(updateUserRequest)
        user.apply {
            name = updateUserRequest.name!!
            email = updateUserRequest.email!!
            gender = updateUserRequest.gender!!
            phone = updateUserRequest.phone!!
            updated_at = Date()
        }

        userRepository.save(user)

        return user.toUserResponse()
    }

    override fun delete(id: String) {
        val user = findProductOrThrow(id)
        userRepository.delete(user)
    }

    override fun list(listUserRequest: ListUserRequest): Page<User> {
        val page = userRepository.findAll(PageRequest.of(listUserRequest.page,listUserRequest.size))
        return page

    }

    override fun changePassword(id: String, changePasswordRequest: ChangePasswordRequest): UserResponse {
        val user = findProductOrThrow(id)
        if(changePasswordRequest.oldPassword != user.password){
            throw CustomException("Old password not valid")
        }

        validationUtil.validate(changePasswordRequest)
        user.apply {
            password = changePasswordRequest.newPassword!!
            updated_at = Date()
        }

        userRepository.save(user)

        return user.toUserResponse()
    }

    private fun findProductOrThrow(id: String): User{
        val user = userRepository.findByIdOrNull(id)
        if(user == null){
            throw NotFoundException()
        }

        return user
    }
}