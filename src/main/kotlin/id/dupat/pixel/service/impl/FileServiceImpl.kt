package id.dupat.pixel.service.impl

import id.dupat.pixel.model.file.FileResponse
import id.dupat.pixel.repository.FileRepository
import id.dupat.pixel.service.FileService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import id.dupat.pixel.entity.Files
import id.dupat.pixel.entity.User
import id.dupat.pixel.exception.NotFoundException
import id.dupat.pixel.util.toFileResponse
import org.springframework.data.repository.findByIdOrNull

@Service
class FileServiceImpl(val fileRepository: FileRepository): FileService {

    override fun uploadFile(file: MultipartFile): String {
        val uploadedFile = Files(
            fileData = file.bytes,
            fileType = file.contentType!!,
            fileName = file.originalFilename!!
        )

        fileRepository.save(uploadedFile)

        return uploadedFile.fileId!!
    }

    override fun updateFile(id: String, file: MultipartFile): String {
        val files = findFileOrThrow(id)

        files.apply {
            fileData = file.bytes
            fileType = file.contentType!!
            fileName = file.originalFilename!!
        }

        fileRepository.save(files)

        return files.fileId!!
    }

    override fun downloadFile(id: String): FileResponse {
        val file = findFileOrThrow(id)

        return file.toFileResponse(true)
    }

    private fun findFileOrThrow(id: String): Files {
        val file = fileRepository.findByIdOrNull(id)
        if(file == null){
            throw NotFoundException()
        }

        return file
    }

}