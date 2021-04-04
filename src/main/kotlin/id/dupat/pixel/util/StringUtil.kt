package id.dupat.pixel.util

import id.dupat.pixel.entity.User
import id.dupat.pixel.model.user.UserResponse

fun User.toUserResponse(): UserResponse{
    return UserResponse(
        id = this.id!!,
        name = this.name!!,
        email = this.email!!,
        password = this.password!!,
        gender = this.gender!!,
        phone = this.phone!!,
        created_at = this.created_at,
        updated_at = this.updated_at
    )
}