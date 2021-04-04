package id.dupat.pixel.util

import id.dupat.pixel.entity.Files
import id.dupat.pixel.entity.User
import id.dupat.pixel.model.file.FileResponse
import id.dupat.pixel.model.user.UserResponse

fun User.toUserResponse(): UserResponse{
    return UserResponse(
        id = this.id!!,
        name = this.name!!,
        email = this.email!!,
        password = this.password!!,
        gender = this.gender!!,
        phone = this.phone!!,
        photo = if(this.photo != null){"/images/${this.photo!!}"} else{null},
        created_at = this.created_at,
        updated_at = this.updated_at
    )
}

fun Files.toFileResponse(needData: Boolean): FileResponse{
    return FileResponse(
        fileId = this.fileId!!,
        fileType = this.fileType,
        fileName = this.fileName,
        fileData = if(needData){this.fileData}else{null}
    )
}