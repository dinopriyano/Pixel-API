package id.dupat.pixel.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Id

class UserResponse (
    val id: String?,

    val name: String?,

    val email: String?,

    val password: String?,

    val gender: String?,

    val phone: String?,

    val created_at: Date?,

    val updated_at: Date?
)