package com.example.baloot_saeedhonari.data.remote

import androidx.lifecycle.LiveData
import com.example.baloot_saeedhonari.api.network.Resource
import com.example.baloot_saeedhonari.data.model.Source
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ArticleApi {
    @GET("top-headlines")
    fun getNewsSource(
        @QueryMap options: Map<String, String>,
        @Query("pageSize") pageNumber: Int =20,
        @Query("page") page: Int = 1,
        ): LiveData<Resource<Source>>

}
