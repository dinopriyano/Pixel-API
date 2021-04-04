package id.dupat.pixel.controller

import id.dupat.pixel.entity.Files
import id.dupat.pixel.model.file.FileResponse
import id.dupat.pixel.service.FileService
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.*


@RestController
class FileUploadController(val fileService: FileService) {

    @GetMapping(
        value = ["/images/{id}"],
        produces = ["application/json"]
    )
    fun getFile(@PathVariable id: String): ResponseEntity<Resource> {
        val uploadedFileToRet: FileResponse? = fileService.downloadFile(id)
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(uploadedFileToRet!!.fileType!!))
            .body(ByteArrayResource(uploadedFileToRet.fileData!!))
    }

}