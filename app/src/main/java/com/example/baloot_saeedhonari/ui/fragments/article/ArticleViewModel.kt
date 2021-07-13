package com.example.baloot_saeedhonari.ui.fragments.article

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baloot_saeedhonari.util.Resource
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
class ArticleViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val ArticleMutable: MutableLiveData<Resource<ArticleResponse>> = MutableLiveData()
    var ArticlePage = 1
    var articleResponse: ArticleResponse? = null

    init {
        getArticles("sports")
    }

    fun getArticles(category: String) = viewModelScope.launch {
        safeArticleCall(category)
    }

    private suspend fun safeArticleCall(countryCode: String){
        ArticleMutable.postValue(Resource.Loading())
        try{
            if(hasInternetConnection(context)){
                val response = articleRepository.getArticle(countryCode, ArticlePage)
                ArticleMutable.postValue(handleBreakingNewsResponse(response))
            }
            else{
                ArticleMutable.postValue(Resource.Error("No Internet Connection"))
            }
        }
        catch (ex : Exception){
            when(ex){
                is IOException -> ArticleMutable.postValue(Resource.Error("Network Failure"))
                else -> ArticleMutable.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleBreakingNewsResponse(response: Response<ArticleResponse>): Resource<ArticleResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                ArticlePage++
                if (articleResponse == null)
                    articleResponse = resultResponse
                else {
                    val oldArticles = articleResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(articleResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}