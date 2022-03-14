package com.fx_trading.lessons.feature_main.feature_common.questions.pre_result

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.fx_trading.common.openTotalUserLevelResult
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.features.databinding.FragmentLastQuestionAnsweredBinding
import com.fx_trading.lessons.features.feature_common.questions.QuestionViewModel
import com.fx_trading.lessons.utils.utils.Logger
import javax.inject.Inject

class LastQuestionAnsweredFragment : BaseFragment<FragmentLastQuestionAnsweredBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLastQuestionAnsweredBinding =
        FragmentLastQuestionAnsweredBinding::inflate

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<QuestionViewModel>

    private val viewModel: QuestionViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            Logger.log("LastQuestionAnsweredFragment", "Bundle result ${arguments}")
            arguments?.let {
                val successQuestions = it.getInt("successQuestions")
                val totalQuestions = it.getInt("totalQuestions")
                val totalAnswers = it.getInt("totalAnswers")

                val questionGroupID = it.getInt("questionGroupID")
                val successAnswers = it.getInt("successAnswers")

                val percentSuccessAnswering =
                    successQuestions.toFloat() / totalQuestions.toFloat() * 100

                if (arguments?.getBoolean("first_question") == true){



                    bottomPanel.setBackgroundColor(Color.GREEN)
                    progressStep.text = "${totalQuestions}/${totalQuestions}"
                    buttonResults.setOnClickListener {
                        val  action = openTotalUserLevelResult(questionGroupID = questionGroupID, successAnswers = successAnswers, totalAnswers = totalAnswers, totalQuestions = totalQuestions, successQuestions = successQuestions)
                        view.findNavController().navigate(action)
                    }
                }

            }


        }

    }

}
