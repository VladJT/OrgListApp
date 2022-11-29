package com.example.orglistapp.utils

interface CommonCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(e: Throwable)
}