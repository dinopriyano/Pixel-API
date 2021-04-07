package id.dupat.pixel.model.user

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.UniqueElements
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class CreateUserRequest (

    val id: String?,

    @field:NotBlank
    val name: String?,

    @field:NotBlank
    @field:Email
    val email: String?,

    @field:NotBlank
    @field:Length(min = 8, max = 20)
    val password: String?,

    @field:NotBlank
    val gender: String?,

    @field:NotBlank
    val phone: String?

)