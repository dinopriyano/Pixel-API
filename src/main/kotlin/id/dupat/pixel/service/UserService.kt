package id.dupat.pixel.service

import id.dupat.pixel.model.user.CreateUserRequest
import id.dupat.pixel.model.user.UpdateUserRequest
import id.dupat.pixel.model.user.UserResponse

interface UserService {

    fun create(createUserRequest: CreateUserRequest): UserResponse

    fun getById(id: String): UserResponse

    fun update(id: String, updateUserRequest: UpdateUserRequest): UserResponse

    fun delete(id: String)

}