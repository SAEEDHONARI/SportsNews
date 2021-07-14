package com.example.baloot_saeedhonari.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.baloot_saeedhonari.api.network.NetworkAndDBBoundResource
import com.example.baloot_saeedhonari.api.network.NetworkResource
import com.example.baloot_saeedhonari.api.network.Resource
import com.example.baloot_saeedhonari.app.AppExecutors
import com.example.baloot_saeedhonari.data.local.ArticleDao
import com.example.baloot_saeedhonari.data.model.Article
import com.example.baloot_saeedhonari.data.model.Source
import com.example.baloot_saeedhonari.data.remote.ArticleApi
import com.example.baloot_saeedhonari.util.ConnectivityUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton



/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 *
 */

@Singleton
class ArticleRepository @Inject constructor(
    private val articleDao: ArticleDao,
    private val apiServices: ArticleApi,
    @ApplicationContext val context: Context,
    private val appExecutors: AppExecutors = AppExecutors()
) {

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     */
    fun getNewsArticles(category: String): LiveData<Resource<List<Article>?>> {
        val data = HashMap<String, String>()
        data["category"] = category
        data["apiKey"] = "ecd0b0b1b44847808195830be0894b97"


        return object : NetworkAndDBBoundResource<List<Article>, Source>(appExecutors) {
            override fun saveCallResult(item: Source) {
                if (item.articles.isNotEmpty()) {
                    articleDao.deleteAllArticles()
                    articleDao.insertArticles(item.articles)
                }
            }

            override fun shouldFetch(data: List<Article>?) =
                (ConnectivityUtil.isConnected(context))

            override fun loadFromDb() = articleDao.getNewsArticles()

            override fun createCall() =
                apiServices.getNewsSource(data)

        }.asLiveData()
    }

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     * LiveData<Resource<NewsSource>>
     */
    fun getNewsArticlesFromServerOnly(countryShortKey: String):
            LiveData<Resource<Source>> {

        val data = HashMap<String, String>()
        data["country"] = countryShortKey
        data["apiKey"] =  "ecd0b0b1b44847808195830be0894b97"


        return object : NetworkResource<Source>() {
            override fun createCall(): LiveData<Resource<Source>> {
                return apiServices.getNewsSource(data)
            }

        }.asLiveData()
    }

}