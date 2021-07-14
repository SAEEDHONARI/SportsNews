package com.example.baloot_saeedhonari.ui.fragments.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AbsListView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baloot_saeedhonari.R
import com.example.baloot_saeedhonari.adapter.ArticlesAdapter
import com.example.baloot_saeedhonari.data.model.Article
import com.example.baloot_saeedhonari.databinding.ArticleFragmentBinding
import com.example.baloot_saeedhonari.util.CATEGORY_NEWS
import com.example.baloot_saeedhonari.util.QUERY_PAGE_SIZE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.article_fragment.*

private const val TAG = "ArticleFragment"

@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.article_fragment), ArticlesAdapter.OnItemClickListener {
    private val viewModel: ArticleViewModel by viewModels()
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ArticleFragmentBinding.bind(view)
        val articleAdapter = ArticlesAdapter(this)

        requireActivity().bottomNavigationView.visibility = View.VISIBLE
        binding.apply {
            rvArticles.apply {
                adapter = articleAdapter
                setHasFixedSize(true)
                addOnScrollListener(this@ArticleFragment.scrollListener)
            }
        }

        viewModel.getNewsArticles(CATEGORY_NEWS).observe(viewLifecycleOwner) {
            when {
                it.status.isLoading() -> {
                    paginationProgressBar.visibility = View.INVISIBLE
                    isLoading = false
                }
                it.status.isSuccessful() -> {
                    it.data?.let { articleResponse ->
                        articleAdapter.submitList(articleResponse.toList())
                        //val totalPages = 20 / QUERY_PAGE_SIZE + 2
                        //isLastPage = viewModel.ArticlePage == totalPages
                        //  if (isLastPage)
                        //    rvArticles.setPadding(0, 0, 0, 0)
                    }
                }
                it.status.isError() -> {
                  //  if (it.errorMessage != null)
                    //ToastUtil.showCustomToast(this, it.errorMessage.toString())
                }
            }
        }
    }
/*    private fun getNewsOfCountry(countryKey: String) {
        viewModel.getNewsArticles(countryKey).observe(this, Observer {
            when {
                it.status.isLoading() -> {
                    news_list.showProgressView()
                }
                it.status.isSuccessful() -> {
                    it?.load(news_list) { news ->
                        adapter.replaceItems(news!!)
                    }
                }
                it.status.isError() -> {
                    if (it.errorMessage != null)
                        ToastUtil.showCustomToast(this, it.errorMessage.toString())
                }
            }
        })
    }*/

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) { //State is scrolling
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val totalVisibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + totalVisibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate =
                isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {
                viewModel.getNewsArticles("us")
                isScrolling = false
            }
        }
    }


    override fun onItemClick(article: Article) {
        /*      val action = BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleFragment(article)
              findNavController().navigate(action)*/
    }
}