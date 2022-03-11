package com.fx_trading.lessons.feature_common.ui.questions.quiz_result

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.feature_common.R
import com.fx_trading.lessons.feature_common.databinding.FragmentLastQuestionAnsweredBinding
import com.fx_trading.lessons.feature_common.databinding.FragmentTotalQuizResultBinding
import com.fx_trading.lessons.feature_common.databinding.FragmentTotalResultQuestionsAfterLessonBinding
import com.fx_trading.lessons.feature_common.openTotalUserLevelResult
import com.fx_trading.lessons.feature_common.ui.questions.*
import com.fx_trading.lessons.utils.utils.Logger
import com.fx_trading.lessons.utils.utils.visible
import javax.inject.Inject

class TotalUserLevelResultFragment : BaseFragment<FragmentTotalQuizResultBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTotalQuizResultBinding =
        FragmentTotalQuizResultBinding::inflate

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<TotalUserLevelResultViewModel>

    private val viewModel: TotalUserLevelResultViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            Logger.log("TotalUserLevelResultFragment", "Bundle result ${arguments}")
            arguments?.let {
                val successQuestions = it.getInt("successQuestions")
                val totalQuestions = it.getInt("totalQuestions")
                val totalAnswers = it.getInt("totalAnswers")

                val questionGroupID = it.getInt("questionGroupID")
                val successAnswers = it.getInt("successAnswers")

                val percentSuccessAnswering = successQuestions.toFloat() / totalQuestions.toFloat() * 100

                when (percentSuccessAnswering.toInt()) {
                    in 0..40 -> {
                        showBeginnerLevel()
                    }
                    in 40..70 -> {
                        showIntermediateLevel()
                    }
                    in 70..100 -> {
                        showExperiencedLevel()
                    }
                }

            }


        }

    }

    private fun showExperiencedLevel() {
        with(binding){
            levelInfo.text = getString(R.string.experienced)
            imageLevel.background = resources.getDrawable(R.drawable.experienced_bottom)
            expertItem.root.visible()
        }
    }

    private fun showIntermediateLevel() {
        with(binding){
            levelInfo.text = getString(R.string.intermediate)
            imageLevel.background = resources.getDrawable(R.drawable.intermediate_bottom)
            intermediateItem.root.visible()
        }
    }

    private fun showBeginnerLevel() {
        with(binding){
            levelInfo.text = getString(R.string.beginner)
            imageLevel.background = resources.getDrawable(R.drawable.beginner_bottom)
            beginnerItem.root.visible()
        }
    }

}
