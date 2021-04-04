package id.dupat.pixel.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User (

    @Id
    val id: String,

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

    @Column(name = "created_at")
    var created_at: Date,

    @Column(name = "updated_at")
    var updated_at: Date?
)