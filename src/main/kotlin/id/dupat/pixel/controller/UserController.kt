package id.dupat.pixel.controller

import id.dupat.pixel.entity.User
import id.dupat.pixel.model.WebPagingResponse
import id.dupat.pixel.model.WebResponse
import id.dupat.pixel.model.user.*
import id.dupat.pixel.service.UserService
import id.dupat.pixel.service.impl.UserServiceImpl
import id.dupat.pixel.util.toUserResponse
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

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

    @GetMapping(
        value = ["/api/users"],
        produces = ["application/json"]
    )
    fun listUser(@RequestParam(value = "size",defaultValue = "10") size: Int, @RequestParam(value = "page",defaultValue = "0") page: Int): WebPagingResponse<List<UserResponse>>{
        val listUserRequest = ListUserRequest(
            size = size,
            page = page
        )
        val pages: Page<User> = userService.list(listUserRequest)
        val users: List<User> = pages.get().collect(Collectors.toList())
        val userResponse: List<UserResponse> = users.map { it.toUserResponse() }

        return WebPagingResponse(
            code = 200,
            error = false,
            message = "Success get user",
            currentPage = page,
            isLast = pages.isLast,
            totalPage = pages.totalPages,
            data = userResponse
        )
    }

    @PutMapping(
        value = ["/api/users/{id_user}/changepassword"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun changePassword(@PathVariable("id_user") id:String, @RequestBody body: ChangePasswordRequest): WebResponse<UserResponse>{
        val response = userService.changePassword(id,body)
        return WebResponse(
            code = 200,
            error = false,
            message = "Success change password",
            data = response
        )
    }
}