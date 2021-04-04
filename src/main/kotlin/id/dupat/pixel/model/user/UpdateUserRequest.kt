package id.dupat.pixel.model.user

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

class UpdateUserRequest (

    @field:NotBlank
    val name: String?,

    @field:NotBlank
    @field:Email
    val email: String?,

    @field:NotBlank
    val gender: String?,

    @field:NotBlank
    val phone: String?

)