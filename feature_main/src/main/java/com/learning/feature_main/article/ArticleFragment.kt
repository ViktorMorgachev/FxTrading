package com.learning.feature_main.article

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.learning.common.State
import com.learning.feature_common.questions.Flag
import com.learning.feature_common.questions.QuestionActivity
import com.learning.feature_example.example.ExampleViewModel
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.core.BaseViewModelFactory
import com.learning.lessons.features.R
import com.learning.lessons.features.databinding.ExampleLayoutBinding
import com.learning.lessons.features.databinding.FragmentArticleBinding
import com.learning.lessons.utils.utils.gone
import com.learning.lessons.utils.utils.visible
import com.learning.navigation.Router
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleFragment : BaseFragment<FragmentArticleBinding>() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<ArticleViewModel>

    private val viewModel: ArticleViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    @Inject
    lateinit var router: Router

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentArticleBinding = FragmentArticleBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val articleID = arguments?.getInt("article_id") ?: -1
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getArticleByID(articleID).collect { state->
                    when (state) {
                        is State.DataState -> {
                            with(binding) {
                                articleToolbar.cancelButton.setOnClickListener {
                                    findNavController().popBackStack()
                                }
                                articleToolbar.toolbarText.text = getString(R.string.article)
                                webView.let { webView ->
                                    webView.settings.apply {
                                        javaScriptEnabled = true
                                        webView.settings.cacheMode = LOAD_CACHE_ELSE_NETWORK
                                        webView.settings.databaseEnabled = true
                                        webView.settings.domStorageEnabled = true
                                        webView.settings.mediaPlaybackRequiresUserGesture = false
                                        webView.settings.javaScriptCanOpenWindowsAutomatically = true
                                        webView.settings.pluginState = WebSettings.PluginState.ON
                                        javaScriptCanOpenWindowsAutomatically = true
                                    }
                                    webView.loadUrl(state.data.url)
                                    if (state.data.questionID > 0){
                                        startQuizBottom.startQuizBottom.visible()
                                        startQuizBottom.startExam.setOnClickListener {
                                            val intent = Intent(requireActivity(), QuestionActivity::class.java)
                                            intent.putExtra(QuestionActivity.key_question_group_id, state.data.questionID)
                                            intent.putExtra(QuestionActivity.key_object_difficulty, state.data.difficulty)
                                            intent.putExtra(QuestionActivity.key_id,  state.data.id)
                                            intent.putExtra(QuestionActivity.key_flag, Flag.Article.name)
                                            startActivity(intent)
                                        }
                                    } else startQuizBottom.startQuizBottom.gone()
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}