package id.dupat.pixel.service

import id.dupat.pixel.model.file.FileResponse
import org.springframework.web.multipart.MultipartFile

interface FileService {

    fun uploadFile(file: MultipartFile): String

    fun updateFile(id:String,file: MultipartFile): String

    fun downloadFile(fileId: String): FileResponse
}