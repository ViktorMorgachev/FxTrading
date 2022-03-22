package com.fx_trading.lessons.feature_main.feature_common.questions

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.paris.Paris
import com.fx_trading.common.FirebaseUtil
import com.fx_trading.common.loadImage
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.domain.entities.quiz.Answer
import com.fx_trading.lessons.domain.entities.quiz.Question
import com.fx_trading.lessons.features.R
import com.fx_trading.lessons.features.databinding.FragmentQuestionsBinding
import com.fx_trading.lessons.utils.utils.gone
import com.fx_trading.lessons.utils.utils.setCompoundDrawables
import com.fx_trading.lessons.utils.utils.visibleOrGone
import javax.inject.Inject
import kotlin.math.roundToInt

class QuestionsFragment : BaseFragment<FragmentQuestionsBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuestionsBinding =
        FragmentQuestionsBinding::inflate

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<QuestionViewModel>

    @Inject
    lateinit var firebaseUtil: FirebaseUtil

    private val viewModel: QuestionViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            recyclerAnswers.layoutManager = LinearLayoutManager(requireContext())
            // Получаем информацию о том что стартовый экзамен или нет, если да,
        }
        viewModel.uiData.observe(viewLifecycleOwner, {
            when (it) {
                is QuestionAction.ShowLoadingAction -> {

                }
                is QuestionAction.ShowLastScreenAction -> {
                    val action = QuestionsFragmentDirections.actionQuestionsFragmentToLastQuestionAnsweredFragment(firstQuestion = it.first_question, questionGroupID = it.questionGroupID, successAnswers = it.successAnswers,
                        totalAnswers = it.totalAnswers, successQuestions = it.successQuestions, totalQuestions = it.totalQuestions)
                    view.findNavController().navigate(action)
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
            Paris.style(checkButon).apply(R.style.button_bottom_light_blue_default)
            checkButon.text = getString(R.string.continues)
            checkButon.setOnClickListener {
                viewModel.nextQuestion()
            }
            when(result){
                 ResultChoices.Success -> {
                     bottomPanel.setBackgroundColor(Color.parseColor("#00C853"))
                     textResult.setTextColor(Color.WHITE)
                     textResult.text = "You’re amazing! :)"
                     val tempData = (recyclerAnswers.adapter as AnswersAdapter).answers
                     recyclerAnswers.adapter = AnswersAdapter(answers = tempData, userAnswers)
                 }
                ResultChoices.AlmostSuccess ->{
                    bottomPanel.setBackgroundColor(Color.parseColor("#ffb703"))
                    textResult.setTextColor(Color.WHITE)
                    textResult.text = "Almost done"
                    val tempData = (recyclerAnswers.adapter as AnswersAdapter).answers
                    recyclerAnswers.adapter = AnswersAdapter(answers = tempData)
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

    @SuppressLint("SetTextI18n")
    private fun showQuestion(quiestion: Question, questionSize: Int, step: Int, succesCount: Int) {
        with(binding) {
            bottomPanel.setBackgroundColor(Color.WHITE)
            Paris.style(checkButon).apply(R.style.quiz_bottom_button_disabled)
            textResult.visibility = View.GONE
            pbHorizontal.progress = ((step / questionSize.toFloat()) * 100).roundToInt()
            quizTitle.text = quiestion.title
            progressStep.text = "${step}/${questionSize}"
            val userChoices = mutableListOf<Answer>()
            recyclerAnswers.adapter = AnswersAdapter(answers = quiestion.answers){
                Paris.style(checkButon).apply(R.style.button_bottom_blue_default)
                checkButon.isEnabled = true
                userChoices.add(it)
            }
            if (quiestion.optional_image_url.isNotEmpty()){
                if (quiestion.optional_image_url.startsWith("gs:")){
                    firebaseUtil.loadImage(quizImage, requireContext(), quiestion.optional_image_url)
                } else{
                    quizImage.loadImage(imageUrl = quiestion.optional_image_url, context = requireContext()){
                        quizImage.gone()
                    }
                }
            } else{
                quizImage.gone()
            }
            quizImage.visibleOrGone(!quiestion.optional_image_url.isEmpty())
            checkButon.text = getString(R.string.check)
            checkButon.setOnClickListener {
                viewModel.checkForCorrect(userChoices)
            }
        }
    }
}
