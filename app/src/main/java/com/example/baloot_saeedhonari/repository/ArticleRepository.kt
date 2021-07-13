package com.example.baloot_saeedhonari.repository

import com.example.baloot_saeedhonari.data.model.Article
import com.example.baloot_saeedhonari.data.model.ArticleResponse
import com.example.baloot_saeedhonari.data.remote.ArticleApi
import com.kadirkuruca.newsapp.data.local.ArticleDao
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val articleApi: ArticleApi,
    private val articleDao: ArticleDao
) {

    suspend fun getArticle(countryCode: String, pageNumber: Int): Response<ArticleResponse> {
        return articleApi.getArticles(countryCode,pageNumber)
    }

    fun getAllArticles() = articleDao.getArticles()

    suspend fun insertArticle(article: Article) = articleDao.insert(article)

    suspend fun deleteArticle(article: Article) = articleDao.delete(article)

    suspend fun deleteAllArticles() = articleDao.deleteAllArticles()
}