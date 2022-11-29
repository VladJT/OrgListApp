package com.example.orglistapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.orglistapp.model.domain.AppState
import com.example.orglistapp.model.entities.OrganizationInfo
import com.example.orglistapp.model.retrofit.OrgInfoRepo
import com.example.orglistapp.model.retrofit.OrgInfoRepoRetrofitImpl
import com.example.orglistapp.utils.CommonCallback
import com.example.orglistapp.utils.postValue

class OrgInfoViewModel(private val org_id: String) : ViewModel() {
    private val liveData: LiveData<AppState<OrganizationInfo>> = MutableLiveData()
    private val repo: OrgInfoRepo = OrgInfoRepoRetrofitImpl()

    fun getLiveData(): LiveData<AppState<OrganizationInfo>> {
        if (liveData.value == null) loadData()
        return liveData
    }

    private fun loadData() {
        liveData.postValue(AppState.Loading(null))
        repo.getOrgInfo(org_id, callback)
    }

    private val callback = object : CommonCallback<OrganizationInfo> {
        override fun onSuccess(data: OrganizationInfo) {
            liveData.postValue(AppState.Success(data))
        }

        override fun onFailure(e: Throwable) {
            liveData.postValue(AppState.Error(e))
        }
    }
}