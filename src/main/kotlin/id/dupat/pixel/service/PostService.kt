package id.dupat.pixel.service

import id.dupat.pixel.entity.Post
import id.dupat.pixel.entity.User
import id.dupat.pixel.model.post.PostRequest
import id.dupat.pixel.model.post.PostResponse
import id.dupat.pixel.model.PagingRequest
import id.dupat.pixel.model.post.UpdatePostRequest
import org.springframework.data.domain.Page
import org.springframework.web.multipart.MultipartFile

interface PostService {
    fun create(image: MultipartFile, postRequest: PostRequest): PostResponse?

    fun delete(postID: String)

    fun list(pagingRequest: PagingRequest, users_id: String?): Page<Post>?

    fun detail(id: String): PostResponse

    fun update(id: String, image: MultipartFile?, updatePostRequest: UpdatePostRequest): PostResponse
}