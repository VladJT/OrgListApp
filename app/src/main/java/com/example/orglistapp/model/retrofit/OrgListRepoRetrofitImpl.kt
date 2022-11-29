package com.example.orglistapp.model.retrofit

import android.os.Looper
import com.example.orglistapp.model.entities.Organization
import com.example.orglistapp.utils.BASE_URL
import com.example.orglistapp.utils.CommonCallback


class OrgListRepoRetrofitImpl : RetrofitImpl(baseUrl = BASE_URL), OrgListRepo {
    private val retrofitImpl = getRetrofitImpl<OrgListAPI>()

    override fun getOrgList(callback: CommonCallback<List<Organization>>) {
        retrofitImpl.getOrgList()
            .enqueue(getCallbackFromRetrofit(object : CommonCallback<List<Organization>> {
                override fun onSuccess(data: List<Organization>) {
                    android.os.Handler(Looper.getMainLooper()).postDelayed({
                        callback.onSuccess(data)
                    }, 0)
                }

                override fun onFailure(e: Throwable) {
                    callback.onFailure(e)
                }
            }))
    }
}