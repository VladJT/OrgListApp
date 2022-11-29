package com.example.orglistapp.model.retrofit

import com.example.orglistapp.model.entities.Organization
import com.example.orglistapp.utils.CommonCallback


interface OrgListRepo {
    fun getOrgList(callback: CommonCallback<List<Organization>>)
}