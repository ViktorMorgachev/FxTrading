package com.fx_trading.lessons.feature_main.feature_common.questions.quiz_result

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.feature_main.activities.MainActivity
import com.fx_trading.lessons.features.R
import com.fx_trading.lessons.features.databinding.FragmentTotalQuizResultBinding
import com.fx_trading.lessons.utils.utils.Logger
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

        viewModel.uiData.observe(viewLifecycleOwner, {
            if (it is TotalResultAction.EnableMainButton){
                with(binding){
                    finishButton.setOnClickListener {
                        startActivity(Intent(requireActivity(), MainActivity::class.java))
                    }
                    finishButton.isEnabled = true
                }
            }
        })

        with(binding) {
            Logger.log("TotalUserLevelResultFragment", "Bundle result ${arguments}")
            arguments?.let {
                val successQuestions = it.getInt("successQuestions")
                val totalQuestions = it.getInt("totalQuestions")
                val totalAnswers = it.getInt("totalAnswers")

                val questionGroupID = it.getInt("questionGroupID")
                val successAnswers = it.getInt("successAnswers")
                var level = 1

                val percentSuccessAnswering =
                    successQuestions.toFloat() / totalQuestions.toFloat() * 100

                when (percentSuccessAnswering.toInt()) {
                    in 0..40 -> {
                        level = 1
                        showBeginnerLevel()
                    }
                    in 40..70 -> {
                        level = 2
                        showIntermediateLevel()
                    }
                    in 70..100 -> {
                        level = 3
                        showExperiencedLevel()
                    }
                }
                viewModel.saveUserResultToDatabase(questionGroupID = questionGroupID, level = level, status = 1)
            }


        }

    }

    private fun showExperiencedLevel() {
        with(binding) {
            levelInfo.text = getString(R.string.experienced)
            imageLevel.background = resources.getDrawable(R.drawable.experienced_bottom)
           // expertItem.
        }
    }

    private fun showIntermediateLevel() {
        with(binding) {
            levelInfo.text = getString(R.string.intermediate)
            imageLevel.background = resources.getDrawable(R.drawable.intermediate_bottom)
         //   intermediateItem.root.visible()
        }
    }

    private fun showBeginnerLevel() {
        with(binding) {
            levelInfo.text = getString(R.string.beginner)
            imageLevel.background = resources.getDrawable(R.drawable.beginner_bottom)
          //  beginnerItem.root.visible()
        }
    }

}
