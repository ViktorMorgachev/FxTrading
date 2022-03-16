package com.fx_trading.lessons.feature_main.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fx_trading.common.State
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.features.databinding.ExampleLayoutBinding
import com.fx_trading.navigation.Router
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ExampleFragment : BaseFragment<ExampleLayoutBinding>() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<ExampleViewModel>

    private val viewModel: ExampleViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    @Inject
    lateinit var router: Router

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> ExampleLayoutBinding = ExampleLayoutBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.getData().collect {
                when(it){
                    is State.LoadingState -> {

                    }
                    is State.ErrorState -> {

                    }
                    is State.DataState ->{
                        with(binding){

                        }
                    }
                }
            }
        }

    }

}