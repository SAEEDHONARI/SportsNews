package com.example.baloot_saeedhonari.data.model

data class ArticleResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)