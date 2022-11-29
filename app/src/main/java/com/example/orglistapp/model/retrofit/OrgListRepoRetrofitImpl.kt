package com.example.orglistapp.model.retrofit

import android.os.Looper
import com.example.orglistapp.model.entities.OrganizationDTO
import com.example.orglistapp.utils.BASE_URL


class OrgListRepoRetrofitImpl : RetrofitImpl(baseUrl = BASE_URL), OrgListRepository {
    private val retrofitImpl = getRetrofitImpl<OrgListAPI>()

    override fun getOrgList(callback: CommonCallback<List<OrganizationDTO>>) {
        retrofitImpl.getOrgList()
            .enqueue(getCallbackFromRetrofit(object : CommonCallback<List<OrganizationDTO>> {
                override fun onSuccess(data: List<OrganizationDTO>) {
                    android.os.Handler(Looper.getMainLooper()).postDelayed({
                        callback.onSuccess(data)
                    }, 1000)
                }

                override fun onFailure(e: Throwable) {
                    callback.onFailure(e)
                }
            }))
    }
}