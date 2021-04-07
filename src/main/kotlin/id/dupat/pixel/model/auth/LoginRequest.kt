package id.dupat.pixel.model.auth

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class LoginRequest (
    @field:NotBlank
    @field:Email
    val email: String?,

    @field:NotBlank
    @field:Length(min = 8, max = 20)
    val password: String?
)