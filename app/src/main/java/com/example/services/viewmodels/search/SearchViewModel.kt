package com.example.services.viewmodels.search

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.services.common.UtilsFunctions
import com.example.services.model.search.SearchResponse
import com.example.services.repositories.search.SearchJobsRepository
import com.example.services.viewmodels.BaseViewModel
import com.google.gson.JsonObject

class SearchViewModel : BaseViewModel() {
    private val mIsUpdating = MutableLiveData<Boolean>()
    private val isClick = MutableLiveData<String>()
    private var searchRepository = SearchJobsRepository()

    private var searchData = MutableLiveData<SearchResponse>()

    /*private var jobsHistoryResponse = MutableLiveData<JobsResponse>()
    private var acceptRejectJob = MutableLiveData<CommonModel>()
    private var startCompleteJob = MutableLiveData<CommonModel>()*/

    init {
        if (UtilsFunctions.isNetworkConnectedWithoutToast()) {
            searchData = searchRepository.search(null)
        }

    }

    fun search(): LiveData<SearchResponse> {
        return searchData
    }


    override fun isLoading(): LiveData<Boolean> {
        return mIsUpdating
    }

    override fun isClick(): LiveData<String> {
        return isClick
    }

    override fun clickListener(v: View) {
        isClick.value = v.resources.getResourceName(v.id).split("/")[1]
    }



    fun search(mJsonObject: JsonObject) {
        if (UtilsFunctions.isNetworkConnected()) {
            searchData = searchRepository.search(mJsonObject)
            mIsUpdating.postValue(true)
        }

    }



}