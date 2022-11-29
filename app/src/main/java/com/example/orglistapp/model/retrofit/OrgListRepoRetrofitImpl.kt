package com.example.orglistapp.model.retrofit

import com.example.orglistapp.model.entities.OrganizationDTO
import com.example.orglistapp.utils.BASE_URL


class OrgListRepoRetrofitImpl : RetrofitImpl(baseUrl = BASE_URL), OrgListRepository {
    private val retrofitImpl = getRetrofitImpl<OrgListAPI>()

    override fun getOrgList(callback: CommonCallback<List<OrganizationDTO>>) {
        retrofitImpl.getOrgList()
            .enqueue(getCallbackFromRetrofit(object : CommonCallback<List<OrganizationDTO>> {
                override fun onSuccess(data: List<OrganizationDTO>) {
                    callback.onSuccess(data)
                }

                override fun onFailure(e: Throwable) {
                    callback.onFailure(e)
                }
            }))
    }
}