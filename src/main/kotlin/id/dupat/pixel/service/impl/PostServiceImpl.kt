package id.dupat.pixel.service.impl

import id.dupat.pixel.entity.Post
import id.dupat.pixel.entity.User
import id.dupat.pixel.exception.NotFoundException
import id.dupat.pixel.model.PagingRequest
import id.dupat.pixel.model.post.PostRequest
import id.dupat.pixel.model.post.PostResponse
import id.dupat.pixel.model.post.UpdatePostRequest
import id.dupat.pixel.repository.PostRepository
import id.dupat.pixel.repository.UserRepository
import id.dupat.pixel.service.FileService
import id.dupat.pixel.service.PostService
import id.dupat.pixel.service.UserService
import id.dupat.pixel.util.ValidationUtil
import id.dupat.pixel.util.toPostResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class PostServiceImpl(val postRepository: PostRepository, val userRepository: UserRepository, val fileService: FileService, val validationUtil: ValidationUtil): PostService {
    override fun create(image: MultipartFile, postRequest: PostRequest): PostResponse? {
        validationUtil.validate(postRequest)

        val user = userRepository.findByIdOrNull(postRequest.users_id)
        val imagesID = fileService.uploadFile(image!!)
        val post = Post(
            title = postRequest.title,
            description = postRequest.description,
            createdAt = Date(),
            updatedAt = null,
            user = user!!,
            image = imagesID
        )

        postRepository.save(post)
        return post.toPostResponse()
    }

    override fun delete(postID: String) {
        val post = findPostOrThrow(postID)

        postRepository.delete(post)
    }

    override fun list(pagingRequest: PagingRequest, users_id: String?): Page<Post>? {
        val page =
            if(users_id.isNullOrEmpty()){
                postRepository.findAll(PageRequest.of(pagingRequest.page-1,pagingRequest.size, Sort.by("createdAt").descending()))
            }
            else{
                val user = userRepository.findByIdOrNull(users_id)
                postRepository.findAllByUser(PageRequest.of(pagingRequest.page-1,pagingRequest.size, Sort.by("createdAt").descending()),user)
            }

        return page
    }

    override fun detail(id: String): PostResponse {
        val post = findPostOrThrow(id)
        return post.toPostResponse()
    }

    override fun update(id: String, newImage: MultipartFile?, updatePostRequest: UpdatePostRequest): PostResponse {
        val post = findPostOrThrow(id)
        validationUtil.validate(updatePostRequest)

        var imageID: String? = null
        if(newImage != null){
            if(post.image != null){
                imageID = fileService.updateFile(post.image!!,newImage)
            }
            else{
                imageID = fileService.uploadFile(newImage)
            }
        }
        else{
            imageID = post.image
        }

        post.apply{
            title = updatePostRequest.title
            description = updatePostRequest.description
            image = imageID
            updatedAt = Date()
        }

        postRepository.save(post)
        return post.toPostResponse()
    }

    private fun findPostOrThrow(id: String): Post{
        val post = postRepository.findByIdOrNull(id)
        if(post == null){
            throw NotFoundException()
        }

        return post
    }
}