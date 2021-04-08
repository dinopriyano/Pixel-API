package id.dupat.pixel.model.post

import id.dupat.pixel.entity.User
import java.util.*
import javax.persistence.*

class PostResponse (
    var id: String? = null,

    var title: String,

    var description: String,

    var image: String,

    var created_at: Date,

    var updated_at: Date?
)