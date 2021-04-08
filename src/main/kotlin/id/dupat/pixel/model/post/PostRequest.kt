package id.dupat.pixel.model.post

import id.dupat.pixel.entity.User
import java.util.*
import javax.persistence.*

class PostRequest (

    var title: String,

    var description: String,

    var users_id: String
)