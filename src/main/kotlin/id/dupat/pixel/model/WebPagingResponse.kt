package id.dupat.pixel.model

class WebPagingResponse <T> (
    val code: Int?,
    val error: Boolean?,
    val message: String?,
    val currentPage: Int?,
    val isLast: Boolean?,
    val totalPage: Int?,
    var data: T?
)