package id.dupat.pixel.controller

import id.dupat.pixel.model.WebResponse
import id.dupat.pixel.model.auth.RegisterResponse
import id.dupat.pixel.model.post.PostRequest
import id.dupat.pixel.model.post.PostResponse
import id.dupat.pixel.model.user.CreateUserRequest
import id.dupat.pixel.service.PostService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class PostController(val postService: PostService) {

    @PostMapping(
        value = ["/api/post"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    fun createPost(@RequestParam("image") image: MultipartFile?, body: PostRequest): WebResponse<PostResponse> {
        val response = postService.create(image!!, body)
        return WebResponse(
            code = 200,
            error = false,
            message = "Post success",
            data = response
        )
    }
}