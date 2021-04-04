package id.dupat.pixel.service

import id.dupat.pixel.entity.User
import id.dupat.pixel.model.user.CreateUserRequest
import id.dupat.pixel.model.user.ListUserRequest
import id.dupat.pixel.model.user.UpdateUserRequest
import id.dupat.pixel.model.user.UserResponse
import org.springframework.data.domain.Page

interface UserService {

    fun create(createUserRequest: CreateUserRequest): UserResponse

    fun getById(id: String): UserResponse

    fun update(id: String, updateUserRequest: UpdateUserRequest): UserResponse

    fun delete(id: String)

    fun list(listUserRequest: ListUserRequest): Page<User>

}