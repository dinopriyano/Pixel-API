package id.dupat.pixel.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
    var id: String? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "gender")
    var gender: String,

    @Column(name = "phone")
    var phone: String,

    @Column(name = "photo")
    var photo: String?,

    @Column(name = "created_at")
    var created_at: Date,

    @Column(name = "updated_at")
    var updated_at: Date?,

    @OneToMany(mappedBy = "user")
    var post: List<Post>? = null
)