package com.example.baloot_saeedhonari.ui.fragments.article

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baloot_saeedhonari.api.network.Resource
import com.example.baloot_saeedhonari.data.model.Article
import com.example.baloot_saeedhonari.data.model.ArticleResponse
import com.example.baloot_saeedhonari.repository.ArticleRepository
import com.example.baloot_saeedhonari.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val newsRepository: ArticleRepository) : ViewModel() {

    /**
     * Loading news articles from internet and database
     */
    private fun newsArticles(category: String): LiveData<Resource<List<Article>?>> =
        newsRepository.getNewsArticles(category)


    fun getNewsArticles(category: String) = newsArticles(category)

    /**
     * Loading news articles from internet only
     */
    private fun newsArticlesFromOnlyServer(countryKey: String) =
        newsRepository.getNewsArticlesFromServerOnly(countryKey)

    fun getNewsArticlesFromServer(countryKey: String) = newsArticlesFromOnlyServer(countryKey)

}