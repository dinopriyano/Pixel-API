package id.dupat.pixel.service

import id.dupat.pixel.model.CreateUserRequest
import id.dupat.pixel.model.UserResponse

interface UserService {

    fun create(createUserRequest: CreateUserRequest): UserResponse

}