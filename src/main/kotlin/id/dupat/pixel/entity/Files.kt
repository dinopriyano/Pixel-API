package id.dupat.pixel.entity

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "files")
data class Files (

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    var fileId: String? = null,

    @Column(name = "fileName")
    var fileName: String,

    @Column(name = "fileType")
    var fileType: String,

    @Lob
    @Column(name = "fileData")
    var fileData: ByteArray
)