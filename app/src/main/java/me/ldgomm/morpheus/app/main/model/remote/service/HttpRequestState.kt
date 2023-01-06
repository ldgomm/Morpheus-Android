package me.ldgomm.morpheus.app.main.model.remote.service

sealed class HttpRequestState<out T> {
    object Idle : HttpRequestState<Nothing>()
    object Loading : HttpRequestState<Nothing>()
    data class Success<T>(val data: T) : HttpRequestState<T>()
    data class Error(val error: Throwable) : HttpRequestState<Nothing>()
}
