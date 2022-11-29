package com.example.orglistapp.model.domain

sealed class AppState<out T> {
    data class Success<T>(val data: T?) : AppState<T>()
    data class Error(val error: Throwable) : AppState<Nothing>()
    data class Loading(val progress: Int?) : AppState<Nothing>()
}