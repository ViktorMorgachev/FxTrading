package com.learning.feature_example.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.learning.common.State
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.core.BaseViewModelFactory
import com.learning.lessons.features.databinding.ExampleLayoutBinding
import com.learning.navigation.Router
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