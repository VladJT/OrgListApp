package com.example.orglistapp.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar

// Live Data to MutableLiveData
fun <T> LiveData<T>.postValue(value: T) {
    val mutable =
        this as? MutableLiveData<T> ?: throw IllegalStateException("it is not MutableData")
    mutable.postValue(value)
}

fun Fragment.snackBar(text: String) {
    this.view?.let { Snackbar.make(it, text, Snackbar.LENGTH_LONG).show() }
}