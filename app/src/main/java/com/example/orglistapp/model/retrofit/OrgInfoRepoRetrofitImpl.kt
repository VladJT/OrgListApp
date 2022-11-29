package com.example.orglistapp.model.retrofit

import com.example.orglistapp.model.entities.OrganizationInfo
import com.example.orglistapp.utils.BASE_URL
import com.example.orglistapp.utils.CommonCallback


class OrgInfoRepoRetrofitImpl : RetrofitImpl(baseUrl = BASE_URL), OrgInfoRepo {
    private val retrofitImpl = getRetrofitImpl<OrgInfoAPI>()

    override fun getOrgInfo(org_id: String, callback: CommonCallback<OrganizationInfo>) {
        retrofitImpl.getOrgInfo(org_id)
            .enqueue(getCallbackFromRetrofit(object : CommonCallback<List<OrganizationInfo>> {
                override fun onSuccess(data: List<OrganizationInfo>) {
                    callback.onSuccess(data[0])
                }

                override fun onFailure(e: Throwable) {
                    callback.onFailure(e)
                }
            }))
    }
}