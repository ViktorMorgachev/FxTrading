package com.learning.feature_main.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.common.State
import com.learning.feature_example.example.ExampleViewModel
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.core.BaseViewModelFactory
import com.learning.lessons.features.databinding.FragmentArticlesBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticlesFragment : BaseFragment<FragmentArticlesBinding>() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<ArticleViewModel>

    private val viewModel: ArticleViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentArticlesBinding = FragmentArticlesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getData().collect { state->
                    when (state) {
                        is State.DataState -> {
                            with(binding) {
                                val articles = state.data.first
                                val passedArticles = state.data.second
                                articlesList.layoutManager = LinearLayoutManager(requireContext())
                                articlesList.adapter = ArticleAdapter(articles, passedArticles){
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}