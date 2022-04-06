package com.learning.feature_main.webinars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.common.State
import com.learning.feature_main.main.MainFragmentDirections
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.core.BaseViewModelFactory
import com.learning.lessons.features.databinding.FragmentWebinarsBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class WebinarsFragment : BaseFragment<FragmentWebinarsBinding>() {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWebinarsBinding =
        FragmentWebinarsBinding::inflate

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<WebinarsViewModel>

    val openWebinarAction: (Int)->Unit = {
        findNavController(this@WebinarsFragment).navigate(
            MainFragmentDirections.actionMainFragmentToWebinarFragment(webinarId = it)
        )
    }

    private val viewModel: WebinarsViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val likeWebinarsAction: (Int) -> Unit = { webinarID ->
            lifecycleScope.launchWhenResumed {
                viewModel.likeWebinar(webinarID = webinarID).collect { state ->
                    when (state) {
                        is State.DataState -> {
                            with(binding) {
                                (recyclerWebinars.adapter as WebinarsAdapter).replaceItem(state.data)
                            }
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.getWebinars().collect {
                    when (it) {
                        is State.DataState -> {
                            with(binding) {
                                recyclerWebinars.layoutManager = LinearLayoutManager(requireContext())
                                recyclerWebinars.adapter = WebinarsAdapter(it.data.toMutableList(), openWebinarAction = openWebinarAction, onLikeWebinarAction = likeWebinarsAction)
                            }
                        }
                    }
                }
                viewModel.subscribeToWebinars().collect { webinars->
                    with(binding) {
                        recyclerWebinars.layoutManager = LinearLayoutManager(requireContext())
                        recyclerWebinars.adapter = WebinarsAdapter(webinars.toMutableList(), openWebinarAction = openWebinarAction, onLikeWebinarAction = likeWebinarsAction)
                    }
                }
            }
        }
    }

}