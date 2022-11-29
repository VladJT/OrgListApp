package com.example.orglistapp.model.entities

import com.google.gson.annotations.SerializedName

data class Organization(
    @SerializedName("id")
    val id: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("name")
    val name: String
)