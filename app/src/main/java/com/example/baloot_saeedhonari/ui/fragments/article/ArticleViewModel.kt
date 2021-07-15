package com.example.baloot_saeedhonari.ui.fragments.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baloot_saeedhonari.api.network.Resource
import com.example.baloot_saeedhonari.data.model.Article
import com.example.baloot_saeedhonari.data.model.ArticleResponse
import com.example.baloot_saeedhonari.repository.ArticleRepository
import com.example.baloot_saeedhonari.util.QUERY_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val newsRepository: ArticleRepository) : ViewModel() {

    /**
     * Loading news articles from internet and database
     */
    var ArticlePage = 1
    val breakingNews: MutableLiveData<Resource<ArticleResponse>> = MutableLiveData()
    var ResourceArticle: Resource<List<Article>?>? = null

    private fun newsArticles(category: String): LiveData<Resource<List<Article>?>> {
        ArticlePage++
        return newsRepository.getNewsArticles(category, QUERY_PAGE_SIZE, ArticlePage)
    }

    fun getNewsArticles(category: String) = newsArticles(category)

    /**
     * Loading news articles from internet only
     */
    private fun newsArticlesFromOnlyServer(category: String, pageSize: Int, page: Int) =
        newsRepository.getNewsArticlesFromServerOnly(category, pageSize, page)

    fun getNewsArticlesFromServer(category: String, pageSize: Int, page: Int) =
        newsArticlesFromOnlyServer(category, pageSize, page)

    fun CleareCache() = newsRepository.CleareCache()


}