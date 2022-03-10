package com.fx_trading.lessons.feature_common.ui.questions

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.paris.Paris
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.domain.entities.quiz.Answer
import com.fx_trading.lessons.domain.entities.quiz.Question
import com.fx_trading.lessons.feature_common.R
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
        with(binding){
            recyclerAnswers.layoutManager = LinearLayoutManager(requireContext())
        }
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
                is QuestionAction.ShowUserChoicesInfo ->{
                    showUserChoicesResult(it.result, it.userChoices)
                }
            }
        })
    }

    private fun showUserChoicesResult(
        result: ResultChoices,
        userAnswers: List<Answer>
    ) {
        with(binding){
            textResult.visibility = View.VISIBLE
            Paris.style(checkButon).apply(R.style.button_default)
            checkButon.text = getString(R.string.continues)
            checkButon.setOnClickListener {
                viewModel.nextQuestion()
            }
            when(result){
                 ResultChoices.Success-> {
                     bottomPanel.setBackgroundColor(Color.GREEN)
                     textResult.setTextColor(Color.WHITE)
                     textResult.text = "You’re amazing! :)"
                     val tempData = (recyclerAnswers.adapter as AnswersAdapter).answers
                     recyclerAnswers.adapter = AnswersAdapter(answers = tempData, userAnswers)
                 }
                else -> {
                    bottomPanel.setBackgroundColor(Color.RED)
                    textResult.setTextColor(Color.WHITE)
                    textResult.text = "Better luck next time!"
                    val tempData = (recyclerAnswers.adapter as AnswersAdapter).answers
                    recyclerAnswers.adapter = AnswersAdapter(answers = tempData, userAnswers)
                }
            }
        }
    }

    private fun showPreResultScreen(){

    }

    @SuppressLint("SetTextI18n")
    override fun showQuestion(quiestion: Question, questionSize: Int, step: Int, succesCount: Int) {
        with(binding) {
            bottomPanel.setBackgroundColor(Color.WHITE)
            Paris.style(checkButon).apply(R.style.button_blue)
            textResult.visibility = View.GONE
            checkButon.isEnabled = false
            pbHorizontal.progress = step / questionSize * 100
            quizTitle.text = quiestion.title
            progressStep.text = "${step}/${questionSize}"
            val userChoices = mutableListOf<Answer>()
            recyclerAnswers.adapter = AnswersAdapter(answers = quiestion.answers){
                checkButon.isEnabled = true
                userChoices.add(it)
            }
            checkButon.text = getString(R.string.check)
            checkButon.setOnClickListener {
                viewModel.checkForCorrect(userChoices)
            }
        }
    }
}
