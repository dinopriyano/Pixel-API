package id.dupat.pixel.repository

import id.dupat.pixel.entity.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface PostRepository: JpaRepository<Post,String> {
}