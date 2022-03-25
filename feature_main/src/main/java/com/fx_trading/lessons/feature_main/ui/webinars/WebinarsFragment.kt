package com.fx_trading.lessons.feature_main.ui.webinars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fx_trading.common.State
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.features.databinding.FragmentWebinarsBinding
import com.fx_trading.lessons.features.ui.webinars.WebinarsView
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class WebinarsFragment:  BaseFragment<FragmentWebinarsBinding>(), WebinarsView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWebinarsBinding = FragmentWebinarsBinding::inflate

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<WebinarsViewModel>

    private val viewModel: WebinarsViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val likeWebinarsAction: (Int)->Unit = { webinarID->
            lifecycleScope.launchWhenResumed {
                viewModel.likeWebinar(webinarID = webinarID).collect { state->
                    when(state){
                        is State.DataState ->{
                            with(binding){
                                (recyclerWebinars.adapter as WebinarsAdapter).replaceItem(state.data)
                            }
                        }
                    }
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.getWebinars().collect {
                when(it){
                    is State.DataState ->{
                        with(binding){
                            recyclerWebinars.layoutManager = LinearLayoutManager(requireContext())
                            recyclerWebinars.adapter = WebinarsAdapter(it.data.toMutableList(), openWebinarAction = {}, onLikeWebinarAction = likeWebinarsAction)
                        }
                    }
                }
            }
        }

    }

}