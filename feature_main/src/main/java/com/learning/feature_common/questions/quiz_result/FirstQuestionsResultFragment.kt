package com.learning.feature_common.questions.quiz_result

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.airbnb.paris.Paris
import com.learning.common.State
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.core.BaseViewModelFactory
import com.learning.feature_main.MainActivity
import com.learning.lessons.features.R
import com.learning.lessons.features.databinding.FragmentFirstResultQuestionsBinding
import com.learning.lessons.utils.utils.Logger
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class FirstQuestionsResultFragment : BaseFragment<FragmentFirstResultQuestionsBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFirstResultQuestionsBinding =
        FragmentFirstResultQuestionsBinding::inflate

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<FirstQuestionsResultViewModel>

    private val viewModel: FirstQuestionsResultViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            Paris.style(finishButton).apply(R.style.bottom_button_disabled)
            finishButton.isEnabled = false
        }
        with(binding) {
            Logger.log("TotalUserLevelResultFragment", "Bundle result ${arguments}")
            arguments?.let {
                val successQuestions = it.getInt("successQuestions")
                val totalQuestions = it.getInt("totalQuestions")
                val questionGroupID = it.getInt("questionGroupID")
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
                lifecycleScope.launchWhenCreated{
                    viewModel.saveUserResultToDatabase(questionGroupID = questionGroupID, level = level).collect {
                       when(it){
                           is State.DataState->{
                               if (it.data == true){
                                   with(binding){
                                       finishButton.setOnClickListener {
                                           startActivity(Intent(requireActivity(), MainActivity::class.java))
                                       }
                                       Paris.style(finishButton).apply(R.style.button_bottom_blue_default)
                                       finishButton.isEnabled = true

                                   }
                               }
                           }
                       }
                    }
                }

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
