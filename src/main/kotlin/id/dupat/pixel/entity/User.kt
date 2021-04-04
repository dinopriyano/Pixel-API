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
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "gender")
    val gender: String,

    @Column(name = "phone")
    val phone: String,

    @Column(name = "created_at")
    val created_at: Date,

    @Column(name = "updated_at")
    val updated_at: Date?
)