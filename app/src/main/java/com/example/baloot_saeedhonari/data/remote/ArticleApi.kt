package com.example.baloot_saeedhonari.data.remote

import com.example.baloot_saeedhonari.data.model.ArticleResponse
import com.example.baloot_saeedhonari.util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET("v2/top-headlines")
    suspend fun getArticles(
        @Query("category") countryCode: String = "sports",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<ArticleResponse>


}