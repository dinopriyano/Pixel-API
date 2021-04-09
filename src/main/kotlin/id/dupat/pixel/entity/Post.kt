package id.dupat.pixel.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Post")
data class Post (

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
    var id: String? = null,

    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "image")
    var image: String,

    @Column(name = "created_at")
    var createdAt: Date,

    @Column(name = "updated_at")
    var updatedAt: Date?,

    @ManyToOne
    var user: User
)