package com.example.services.model.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SearchResponse {
    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("body")
    @Expose
    var body: Body? = null

    class Body {
        @SerializedName("responseData")
        @Expose
        var responseData: ResponseData? = null
    }

    class ResponseData {
        @SerializedName("serviceData")
        @Expose
        var serviceData: List<ServiceDatum>? = null

        /*@SerializedName("cartCategoryType")
        @Expose
        var cartCategoryType: String? = null*/
    }

    class ServiceDatum {
        @SerializedName("icon")
        @Expose
        var icon: String? = null

        @SerializedName("thumbnail")
        @Expose
        var thumbnail: String? = null

        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("categoryId")
        @Expose
        var categoryId: String? = null
    }

}



