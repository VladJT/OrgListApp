package com.example.orglistapp.model.retrofit

import com.example.orglistapp.model.entities.OrganizationInfo
import com.example.orglistapp.utils.CommonCallback


interface OrgInfoRepo {
    fun getOrgInfo(org_id: String, callback: CommonCallback<OrganizationInfo>)
}