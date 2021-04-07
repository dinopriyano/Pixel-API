package id.dupat.pixel.repository

import id.dupat.pixel.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User,String> {

    fun findByEmailAndPassword(email: String,password: String): User?

}