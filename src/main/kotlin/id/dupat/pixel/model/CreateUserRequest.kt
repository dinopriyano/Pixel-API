package id.dupat.pixel.model

import javax.persistence.Column

class CreateUserRequest (

    val id: String?,

    val name: String?,

    val email: String?,

    val password: String?,

    val gender: String?,

    val phone: String?,

)