package com.example.baloot_saeedhonari.data.remote

import androidx.lifecycle.LiveData
import com.example.baloot_saeedhonari.api.network.Resource
import com.example.baloot_saeedhonari.data.model.Source
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ArticleApi {
    /**
     * Fetch news articles from Google news using GET API Call on given Url
     * Url would be something like this top-headlines?country=my&apiKey=XYZ
     */
    @GET("top-headlines")
    fun getNewsSource(@QueryMap options: Map<String, String>): LiveData<Resource<Source>>

}
