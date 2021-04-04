package id.dupat.pixel.controller

import id.dupat.pixel.model.CreateUserRequest
import id.dupat.pixel.model.UserResponse
import id.dupat.pixel.model.WebResponse
import id.dupat.pixel.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val userService: UserService) {

    @PostMapping(
        value = ["/api/users"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createUser(@RequestBody body: CreateUserRequest): WebResponse<UserResponse>{
        val response = userService.create(body)
        return WebResponse(
            code = 200,
            error = false,
            message = "Success create user",
            data = response
        )
    }
}