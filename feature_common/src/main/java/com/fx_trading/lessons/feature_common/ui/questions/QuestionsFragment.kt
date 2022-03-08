package com.fx_trading.lessons.feature_common.ui.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.domain.entities.quiz.Question
import com.fx_trading.lessons.feature_common.databinding.FragmentQuestionsBinding
import javax.inject.Inject

class QuestionsFragment : BaseFragment<FragmentQuestionsBinding>(), QuiestionsView {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuestionsBinding =
        FragmentQuestionsBinding::inflate

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<QuestionViewModel>

    private val viewModel: QuestionViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiData.observe(viewLifecycleOwner, {
            when (it) {
                is QuestionAction.ShowLoadingAction -> {

                }
                is QuestionAction.ShowLastScreenAction -> {

                }
                is QuestionAction.ShowResultAction -> {

                }
                is QuestionAction.ShowQuestionAction -> {
                    showQuestion(it.quiestion, questionSize = it.questionSize, step = it.step, succesCount = it.succesCount)
                }
            }
        })
    }

    override fun showQuestion(quiestion: Question, questionSize: Int, step: Int, succesCount: Int) {
        with(binding) {
            pbHorizontal.progress = step / questionSize * 100
            quizDescribe.text = quiestion.description
            progressStep.text = "${step / questionSize}"
            recyclerAnswers.adapter = AnswersAdapter(answers = quiestion.answers)
        }
    }
}
