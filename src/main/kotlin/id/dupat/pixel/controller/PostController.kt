package id.dupat.pixel.controller

import id.dupat.pixel.entity.Post
import id.dupat.pixel.entity.User
import id.dupat.pixel.model.PagingRequest
import id.dupat.pixel.model.WebPagingResponse
import id.dupat.pixel.model.WebResponse
import id.dupat.pixel.model.auth.RegisterResponse
import id.dupat.pixel.model.post.PostRequest
import id.dupat.pixel.model.post.PostResponse
import id.dupat.pixel.model.user.CreateUserRequest
import id.dupat.pixel.model.user.UserResponse
import id.dupat.pixel.service.PostService
import id.dupat.pixel.util.toPostResponse
import id.dupat.pixel.util.toUserResponse
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.stream.Collectors

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

    @DeleteMapping(
        value = ["/api/post/{id_post}"],
        produces = ["application/json"]
    )
    fun deletePost(@PathVariable("id_post") id: String): WebResponse<PostResponse>{
        val response = postService.delete(id)
        return WebResponse(
            code = 200,
            error = false,
            message = "Post deleted",
            data = null
        )
    }

    @GetMapping(
        value = ["/api/post"],
        produces = ["application/json"]
    )
    fun listPost(@RequestParam(value = "size",defaultValue = "10") size: Int, @RequestParam(value = "page",defaultValue = "1") page: Int): WebPagingResponse<List<PostResponse>> {
        val pagingRequest = PagingRequest(
            size = size,
            page = page
        )
        val pages: Page<Post> = postService.list(pagingRequest)!!
        val posts: List<Post> = pages.get().collect(Collectors.toList())
        val userResponse: List<PostResponse> = posts.map { it.toPostResponse() }

        return WebPagingResponse(
            code = 200,
            error = false,
            message = "Success get post",
            currentPage = page,
            isLast = pages.isLast,
            totalPage = pages.totalPages,
            data = userResponse
        )
    }
}