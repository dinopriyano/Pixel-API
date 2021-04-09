package id.dupat.pixel.service.impl

import id.dupat.pixel.entity.Post
import id.dupat.pixel.entity.User
import id.dupat.pixel.model.post.PostRequest
import id.dupat.pixel.model.post.PostResponse
import id.dupat.pixel.repository.PostRepository
import id.dupat.pixel.repository.UserRepository
import id.dupat.pixel.service.FileService
import id.dupat.pixel.service.PostService
import id.dupat.pixel.service.UserService
import id.dupat.pixel.util.toPostResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class PostServiceImpl(val postRepository: PostRepository, val userRepository: UserRepository, val fileService: FileService): PostService {
    override fun create(image: MultipartFile, postRequest: PostRequest): PostResponse? {
        val user = userRepository.findByIdOrNull(postRequest.users_id)
        val imagesID = fileService.uploadFile(image!!)
        val post = Post(
            title = postRequest.title,
            description = postRequest.description,
            created_at = Date(),
            updated_at = null,
            user = user!!,
            image = imagesID
        )

        postRepository.save(post)
        return post.toPostResponse()
    }
}