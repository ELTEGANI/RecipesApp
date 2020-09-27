package com.ahmedalaa.gamesapp.utils


sealed class ApiResponse<out T : Any> {
    data class Success<out T : Any>(val data: T?) : ApiResponse<T>()
    data class Error(val errorMsg: Int) : ApiResponse<Nothing>()
    object InProgress : ApiResponse<Nothing>()
    object None : ApiResponse<Nothing>()

}

inline fun <T : Any> ApiResponse<T>.onSuccess(action: (T) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Success) data?.let { action(it) }
    return this
}

inline fun <T : Any> ApiResponse<T>.onError(action: (Int) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Error)
        action(this.errorMsg)
    return this
}

inline fun <T : Any> ApiResponse<T>.onProgress(action: () -> Unit): ApiResponse<T> {
    if (this is ApiResponse.InProgress)
        action()
    return this
}