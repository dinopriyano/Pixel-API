package id.dupat.pixel.model.post

import id.dupat.pixel.entity.User
import id.dupat.pixel.model.user.UserResponse
import java.util.*
import kotlin.collections.HashMap

class ListPostResponse (
    var id: String? = null,

    var title: String,

    var description: String,

    var image: String,

    var created_at: Date,

    var updated_at: Date?,

    var users: HashMap<String,String?>
)