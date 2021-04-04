package id.dupat.pixel.model

class WebResponse<T> (
    val code: Int?,
    val error: Boolean?,
    val message: String?,
    var data: T?
)