package com.fx_trading.lessons.feature_common.ui.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.feature_common.databinding.FragmentLastQuestionAnsweredBinding
import com.fx_trading.lessons.feature_common.ui.questions.*
import javax.inject.Inject

class TotalResultQuestions : BaseFragment<FragmentLastQuestionAnsweredBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLastQuestionAnsweredBinding =
        FragmentLastQuestionAnsweredBinding::inflate

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<QuestionViewModel>

    private val viewModel: QuestionViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
