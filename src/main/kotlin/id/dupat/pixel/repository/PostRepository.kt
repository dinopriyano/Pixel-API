package id.dupat.pixel.repository

import id.dupat.pixel.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post,String> {
}