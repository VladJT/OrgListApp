package com.example.orglistapp.model.retrofit

import com.example.orglistapp.model.entities.OrganizationDTO


interface OrgListRepository {
    fun getOrgList(callback: CommonCallback<List<OrganizationDTO>>)
}