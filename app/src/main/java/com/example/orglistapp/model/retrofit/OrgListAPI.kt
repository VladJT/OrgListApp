package com.example.orglistapp.model.retrofit

import com.example.orglistapp.model.entities.OrganizationDTO
import retrofit2.Call
import retrofit2.http.GET

interface OrgListAPI {
    @GET("")
    fun getOrgList(): Call<List<OrganizationDTO>>
}