package id.dupat.pixel.model.auth

import java.util.*

class LoginResponse (
    val id: String?,

    val name: String?,

    val email: String?,

    val password: String?,

    val gender: String?,

    val phone: String?,

    val photo: String?,

    val created_at: Date?,

    val updated_at: Date?,

    val token: String?
)