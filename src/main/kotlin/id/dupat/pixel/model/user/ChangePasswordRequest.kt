package id.dupat.pixel.model.user

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

class ChangePasswordRequest (

    @field:NotBlank
    val oldPassword: String,

    @field:NotBlank
    @field:Length(min = 8, max = 20)
    val newPassword: String
)