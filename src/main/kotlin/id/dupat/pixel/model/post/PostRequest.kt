package id.dupat.pixel.model.post

import id.dupat.pixel.entity.User
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank

class PostRequest (

    @field:NotBlank
    var title: String,

    @field:NotBlank
    var description: String,

    @field:NotBlank
    var users_id: String
)