package id.dupat.pixel.repository

import id.dupat.pixel.entity.Files
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<Files, String>