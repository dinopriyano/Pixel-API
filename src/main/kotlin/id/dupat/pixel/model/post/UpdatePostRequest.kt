package id.dupat.pixel.model.post

import javax.validation.constraints.NotBlank

class UpdatePostRequest (
    @field:NotBlank
    var title: String,

    @field:NotBlank
    var description: String
)