package id.dupat.pixel.service

import id.dupat.pixel.entity.User
import id.dupat.pixel.model.PagingRequest
import id.dupat.pixel.model.user.*
import org.springframework.data.domain.Page
import org.springframework.web.multipart.MultipartFile

interface UserService {

    fun create(photo: MultipartFile?, createUserRequest: CreateUserRequest): UserResponse

    fun getById(id: String): UserResponse

    fun update(id: String,photo: MultipartFile?, updateUserRequest: UpdateUserRequest): UserResponse

    fun delete(id: String)

    fun list(pagingRequest: PagingRequest): Page<User>

    fun changePassword(id: String, changePasswordRequest: ChangePasswordRequest): UserResponse

}