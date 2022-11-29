package com.example.orglistapp.model.retrofit

interface CommonCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(e: Throwable)
}