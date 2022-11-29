package com.example.orglistapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.orglistapp.model.domain.AppState
import com.example.orglistapp.model.entities.OrganizationDTO
import com.example.orglistapp.model.retrofit.CommonCallback
import com.example.orglistapp.model.retrofit.OrgListRepoRetrofitImpl
import com.example.orglistapp.model.retrofit.OrgListRepository
import com.example.orglistapp.utils.postValue

class OrgListViewModel(
    private val liveData: LiveData<AppState<List<OrganizationDTO>>> =
        MutableLiveData(),
    private val retrofitImpl: OrgListRepository = OrgListRepoRetrofitImpl()
) : ViewModel() {

    fun getLiveData(): LiveData<AppState<List<OrganizationDTO>>> {
        return liveData
    }

    fun loadData() {
        retrofitImpl.getOrgList(callback)
    }

    private val callback = object : CommonCallback<List<OrganizationDTO>> {
        override fun onSuccess(result: List<OrganizationDTO>) {
            liveData.postValue(AppState.Success(result))
        }

        override fun onFailure(e: Throwable) {
            liveData.postValue(AppState.Error(e))
        }
    }
}