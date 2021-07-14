package com.example.baloot_saeedhonari.data.model

import com.google.gson.annotations.SerializedName


data class Source(
    @SerializedName("status") var status: String = "",
    @SerializedName("source") var source: String = "",
    @SerializedName("sortBy") var sortBy: String = "",
    @SerializedName("articles") var articles: List<Article> = emptyList()
)