package id.dupat.pixel.util

import id.dupat.pixel.entity.Files
import id.dupat.pixel.entity.Post
import id.dupat.pixel.entity.User
import id.dupat.pixel.model.auth.LoginResponse
import id.dupat.pixel.model.auth.RegisterResponse
import id.dupat.pixel.model.file.FileResponse
import id.dupat.pixel.model.post.ListPostResponse
import id.dupat.pixel.model.post.PostResponse
import id.dupat.pixel.model.user.UserResponse
import java.util.stream.Collectors

import org.springframework.security.core.GrantedAuthority

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import kotlin.collections.HashMap


fun getBaseUrl(): String{
    return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
}

fun User.toUserResponse(): UserResponse{
    return UserResponse(
        id = this.id!!,
        name = this.name!!,
        email = this.email!!,
        gender = this.gender!!,
        phone = this.phone!!,
        photo = if(this.photo != null){"${getBaseUrl()}/api/images/${this.photo!!}"} else{null},
        created_at = this.createdAt,
        updated_at = this.updatedAt
    )
}

fun User.toRegisterResponse(): RegisterResponse{
    return RegisterResponse(
        id = this.id!!,
        name = this.name!!,
        email = this.email!!,
        gender = this.gender!!,
        phone = this.phone!!,
        photo = if(this.photo != null){"${getBaseUrl()}/api/images/${this.photo!!}"} else{null},
        created_at = this.createdAt,
        updated_at = this.updatedAt
    )
}

fun User.toLoginResponse(token: String): LoginResponse{
    return LoginResponse(
        id = this.id!!,
        name = this.name!!,
        email = this.email!!,
        password = this.password!!,
        gender = this.gender!!,
        phone = this.phone!!,
        photo = if(this.photo != null){"${getBaseUrl()}/api/images/${this.photo!!}"} else{null},
        created_at = this.createdAt,
        updated_at = this.updatedAt,
        token = token
    )
}

fun Post.toPostResponse(): PostResponse{
    return PostResponse(
        id = this.id!!,
        title = this.title!!,
        description = this.description!!,
        image = "${getBaseUrl()}/api/images/${this.image!!}",
        created_at = this.createdAt!!,
        updated_at = this.updatedAt
    )
}

fun Post.toListPostResponse(): ListPostResponse{
    val map = HashMap<String,String?>()
    map["id"] = this.user.id
    map["name"] = this.user.name
    map["photo"] = "${getBaseUrl()}/api/images/${this.user.photo!!}"

    return ListPostResponse(
        id = this.id!!,
        title = this.title!!,
        description = this.description!!,
        image = "${getBaseUrl()}/api/images/${this.image!!}",
        created_at = this.createdAt!!,
        updated_at = this.updatedAt,
        users = map
    )
}

fun String.getJWTToken(): String{
    val secretKey = "SecretKey"
    val grantedAuthorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList("ROLE_USER")

    val token = Jwts
        .builder()
        .setId("Pixel")
        .setSubject(this)
        .claim("authorities",
            grantedAuthorities.stream()
                .map { obj: GrantedAuthority -> obj.authority }
                .collect(Collectors.toList()))
        .setIssuedAt(Date(System.currentTimeMillis()))
        .setExpiration(Date(System.currentTimeMillis() + 600000))
        .signWith(
            SignatureAlgorithm.HS512,
            secretKey.toByteArray()
        ).compact()

    return "Bearer $token"
}

fun Files.toFileResponse(needData: Boolean): FileResponse{
    return FileResponse(
        fileId = this.fileId!!,
        fileType = this.fileType,
        fileName = this.fileName,
        fileData = if(needData){this.fileData}else{null}
    )
}