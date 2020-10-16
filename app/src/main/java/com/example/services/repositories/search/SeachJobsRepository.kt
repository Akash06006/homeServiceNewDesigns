package com.example.services.repositories.search

import androidx.lifecycle.MutableLiveData
import com.example.services.R
import com.example.services.api.ApiClient
import com.example.services.api.ApiResponse
import com.example.services.api.ApiService
import com.example.services.application.MyApplication
import com.example.services.common.UtilsFunctions
import com.example.services.model.search.SearchResponse
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Response

class SearchJobsRepository {

    private var searchData: MutableLiveData<SearchResponse>? = null
    private val gson = GsonBuilder().serializeNulls().create()

    init {
        searchData = MutableLiveData()
    }


    fun search(jsonObject: JsonObject?) : MutableLiveData<SearchResponse> {
        if (jsonObject != null) {
            val mApiService = ApiService<JsonObject>()
            mApiService.get(
                object : ApiResponse<JsonObject> {
                    override fun onResponse(mResponse : Response<JsonObject>) {
                        val searchResponse = if (mResponse.body() != null)
                            gson.fromJson<SearchResponse>(
                                "" + mResponse.body(),
                                SearchResponse::class.java
                            )
                        else {
                            gson.fromJson<SearchResponse>(
                                mResponse.errorBody()!!.charStream(),
                                SearchResponse::class.java
                            )
                        }
                        searchData!!.postValue(searchResponse)
                    }

                    override fun onError(mKey : String) {
                        UtilsFunctions.showToastError(MyApplication.instance.getString(R.string.internal_server_error))
                        searchData!!.postValue(null)
                    }

                }, ApiClient.getApiInterface().search(jsonObject)
            )

        }
        return searchData!!

    }

}