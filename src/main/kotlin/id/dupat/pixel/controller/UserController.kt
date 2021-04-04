package id.dupat.pixel.controller

import id.dupat.pixel.model.user.CreateUserRequest
import id.dupat.pixel.model.user.UserResponse
import id.dupat.pixel.model.WebResponse
import id.dupat.pixel.model.user.UpdateUserRequest
import id.dupat.pixel.service.UserService
import org.springframework.web.bind.annotation.*

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

    @GetMapping(
        value = ["/api/users/{id_user}"],
        produces = ["application/json"]
    )
    fun getUser(@PathVariable("id_user") id: String): WebResponse<UserResponse>{
        val response = userService.getById(id)
        return WebResponse(
            code = 200,
            error = false,
            message = "Success get user",
            data = response
        )
    }

    @PutMapping(
        value = ["/api/users/{id_user}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateUser(@PathVariable("id_user") id:String, @RequestBody body: UpdateUserRequest): WebResponse<UserResponse>{
        val response = userService.update(id,body)
        return WebResponse(
            code = 200,
            error = false,
            message = "Success update user",
            data = response
        )
    }

    @DeleteMapping(
        value = ["/api/users/{id_user}"],
        produces = ["application/json"]
    )
    fun deleteUser(@PathVariable("id_user") id: String): WebResponse<UserResponse>{
        val response = userService.delete(id)
        return WebResponse(
            code = 200,
            error = false,
            message = "Success delete user",
            data = null
        )
    }
}