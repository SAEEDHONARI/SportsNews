package com.example.baloot_saeedhonari.ui.fragments.description

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.baloot_saeedhonari.R
import com.example.baloot_saeedhonari.databinding.FragmentDescriptionNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_description_news.*


@AndroidEntryPoint
class DescriptionNewsFragment : Fragment(R.layout.fragment_description_news) {
    private val args by navArgs<DescriptionNewsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDescriptionNewsBinding.bind(view)
        binding.apply {
            val article = args.description
            Glide.with(requireContext())
                .load(article.urlToImage)
                .into(img_news)
            tvDescNews.text = article.description
            tvTitleNews.text = article.title
            tvTvPublishedAtNews.text = article.author
            }

    }
}