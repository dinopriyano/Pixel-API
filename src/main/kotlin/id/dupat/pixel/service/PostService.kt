package id.dupat.pixel.service

import id.dupat.pixel.model.post.PostRequest
import id.dupat.pixel.model.post.PostResponse
import org.springframework.web.multipart.MultipartFile

interface PostService {
    fun create(image: MultipartFile, postRequest: PostRequest): PostResponse?
}