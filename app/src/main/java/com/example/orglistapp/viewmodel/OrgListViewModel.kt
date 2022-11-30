package com.example.orglistapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.orglistapp.model.domain.AppState
import com.example.orglistapp.model.entities.Organization
import com.example.orglistapp.model.retrofit.OrgListRepo
import com.example.orglistapp.model.retrofit.OrgListRepoRetrofitImpl
import com.example.orglistapp.utils.CommonCallback
import com.example.orglistapp.utils.postValue

class OrgListViewModel : ViewModel() {
    private val liveData: LiveData<AppState<List<Organization>>> = MutableLiveData()
    private val repo: OrgListRepo = OrgListRepoRetrofitImpl()

    fun getLiveData(): LiveData<AppState<List<Organization>>> {
        if (liveData.value == null) loadData()
        return liveData
    }

    private fun loadData() {
        liveData.postValue(AppState.Loading(null))
        repo.getOrgList(callback)
    }

    private val callback = object : CommonCallback<List<Organization>> {
        override fun onSuccess(data: List<Organization>) {
            liveData.postValue(AppState.Success(data))
        }

        override fun onFailure(e: Throwable) {
            liveData.postValue(AppState.Error(e))
        }
    }
}