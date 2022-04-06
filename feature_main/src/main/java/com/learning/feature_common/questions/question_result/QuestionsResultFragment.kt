package com.learning.feature_common.questions.question_result

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.airbnb.paris.Paris
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.core.BaseViewModelFactory
import com.learning.lessons.features.R
import com.learning.lessons.features.databinding.FragmentTotalResultQuestionsBinding
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import kotlin.math.roundToInt

class QuestionsResultFragment : BaseFragment<FragmentTotalResultQuestionsBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTotalResultQuestionsBinding =
        FragmentTotalResultQuestionsBinding::inflate

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<QuestionResultViewModel>

    private val viewModel: QuestionResultViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            arguments?.let {
                val successQuestions = it.getInt("successQuestions")
                val totalQuestions = it.getInt("totalQuestions")
                val questionGroupID = it.getInt("questionGroupID")
                val correctForSucess = it.getInt("correctForSuccess")
                val difficulty = it.getInt("difficulty")
                val id = it.getInt("id")
                val flag = it.getString("flag") ?: ""

                Paris.style(finishButton).apply(R.style.bottom_button_disabled)
                val percentSuccessAnswering =
                    (successQuestions.toFloat() / totalQuestions.toFloat() * 100).roundToInt()


                if (successQuestions < correctForSucess) {
                    textMainResult.text =  resources.getString(R.string.failed)
                    progressToolbar.pbHorizontal.progressTintList = ColorStateList.valueOf(Color.parseColor("#FF3D00"))
                    motivationText.text = resources.getString(R.string.motivation_failed)
                    progressToolbar.cancelButton.setImageDrawable( ResourcesCompat.getDrawable(resources, R.drawable.cancel_red, null))
                    resultImage.setImageDrawable( ResourcesCompat.getDrawable(resources, R.drawable.faillure_result, null))
                } else {
                    textMainResult.text =  resources.getString(R.string.great_job)
                    progressToolbar.pbHorizontal.progressTintList = ColorStateList.valueOf(Color.parseColor("#01AC48"))
                    motivationText.text = resources.getString(R.string.motivation_success)
                    progressToolbar.cancelButton.setImageDrawable( ResourcesCompat.getDrawable(resources, R.drawable.checked_green, null))
                    resultImage.setImageDrawable( ResourcesCompat.getDrawable(resources, R.drawable.success_result, null))

                    // Сохранить результат в usersInfo о том что пользователь прошёл тест успешно по уроку, и сохранить в question_list что пользователь прошёл курс

                }

                progressToolbar.pbHorizontal.progress = 100
                progressToolbar.progressStep.text = "${totalQuestions}/${totalQuestions}"
                percentResult.text = "${percentSuccessAnswering}%"
                countTotalQuestions.text = "$totalQuestions"
                countResultAnswers.text = "$successQuestions"
                difficultyItem.setDifficulty(difficulty)


                when (difficulty) {
                    1 -> {
                        lessonDifficultyDescription.text = resources.getString(R.string.lesson_easy)
                    }
                    2 -> {
                        lessonDifficultyDescription.text = resources.getString(R.string.lesson_hard)
                    }
                    3 ->{
                        lessonDifficultyDescription.text = resources.getString(R.string.lesson_medium)
                    }

                }
             lifecycleScope.launchWhenResumed {
                 viewModel.saveExamResult(materialID = id, questionID = questionGroupID, flag = flag).collect{
                     Paris.style(finishButton).apply(R.style.button_bottom_blue_default)
                     finishButton.setOnClickListener {
                         requireActivity().finish()
                     }
                 }
             }
            }
        }

    }

}
