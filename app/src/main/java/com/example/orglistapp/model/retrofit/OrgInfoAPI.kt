package com.example.orglistapp.model.retrofit

import com.example.orglistapp.model.entities.OrganizationInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OrgInfoAPI {
    @GET("test.php")
    fun getOrgInfo(@Query("id") org_id: String): Call<List<OrganizationInfo>>
}