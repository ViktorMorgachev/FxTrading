package com.fx_trading.lessons.feature_main.feature_common.questions

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.paris.Paris
import com.fx_trading.common.FirebaseUtil
import com.fx_trading.common.State
import com.fx_trading.common.getIntExtra
import com.fx_trading.common.loadImage
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.domain.entities.quiz.Answer
import com.fx_trading.lessons.domain.entities.quiz.Question
import com.fx_trading.lessons.feature_main.activities.QuestionActivity.Companion.key_lesson_difficulty
import com.fx_trading.lessons.feature_main.activities.QuestionActivity.Companion.key_lesson_id
import com.fx_trading.lessons.feature_main.activities.QuestionActivity.Companion.key_question_group_id
import com.fx_trading.lessons.features.R
import com.fx_trading.lessons.features.databinding.FragmentQuestionsBinding
import com.fx_trading.lessons.utils.utils.Logger
import com.fx_trading.lessons.utils.utils.gone
import com.fx_trading.lessons.utils.utils.visibleOrGone
import kotlinx.coroutines.flow.collect
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
        with(binding) {
            recyclerAnswers.layoutManager = LinearLayoutManager(requireContext())
        }
        lifecycleScope.launchWhenResumed {
            val questionID = requireActivity().getIntExtra(key_question_group_id, null) as Int?
            // Получаем информацию о том что стартовый экзамен или нет, если да,
            viewModel.getQuestions(questionID).collect { state ->
                when (state) {
                    is State.DataState -> {
                        nextQuestion()
                    }
                }
            }
        }
    }

    private fun nextQuestion() {
        lifecycleScope.launchWhenResumed {
            viewModel.nextQuestion().collect {
                when (it) {
                    is State.DataState -> {
                        val data = it.data
                        if (data != null) {
                            showQuestion(quiestion = data)
                        }
                    }
                }
            }
        }

    }

    private fun showUserChoicesResult(
        result: ResultChoices,
        userAnswers: List<Answer>,
        lastQuestion: Boolean,
    ) {
        with(binding) {
            textResult.visibility = View.VISIBLE
            Paris.style(checkButon).apply(R.style.button_bottom_light_blue_default)
            if (lastQuestion) {
                checkButon.text = getString(R.string.results)
                checkButon.setOnClickListener {
                    try {
                        if (requireActivity().getIntExtra(key_question_group_id, 0) as Int == 0) {
                            findNavController().navigate(QuestionsFragmentDirections.actionQuestionsFragmentToFirstQuestionsResultFragment(questionGroupID = viewModel.questionGroupID, successQuestion = viewModel.successAnswers, totalQuestion = viewModel.questionsSize))
                        } else {
                            val difficultyLesson = requireActivity().getIntExtra(key_lesson_difficulty, 0) as Int
                            val lessonID = requireActivity().getIntExtra(key_lesson_id, 0) as Int
                            findNavController().navigate(QuestionsFragmentDirections.actionQuestionsFragmentToQuestionsResultFragment(
                                questionGroupID = viewModel.questionGroupID,
                                successQuestions = viewModel.successAnswers,
                                totalQuestions = viewModel.questionsSize,
                                correctForSuccess = viewModel.correctForSuccess,
                                lessonDifficulty = difficultyLesson, lessonID = lessonID))
                        }
                    } catch (e: Exception){
                        Logger.log("QuestionsFragment", exception =  e)
                    }

                }
            } else {
                checkButon.text = getString(R.string.continues)
                checkButon.setOnClickListener {
                    nextQuestion()
                }
            }
            when (result) {
                ResultChoices.Success -> {
                    bottomPanel.setBackgroundColor(Color.parseColor("#00C853"))
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

    @SuppressLint("SetTextI18n")
    private fun showQuestion(
        quiestion: Question
    ) {
        with(binding) {
            bottomPanel.setBackgroundColor(Color.WHITE)
            Paris.style(checkButon).apply(R.style.quiz_bottom_button_disabled)
            textResult.visibility = View.GONE
            quizToolbar.pbHorizontal.progress = ((viewModel.step / viewModel.questionsSize.toFloat()) * 100).roundToInt()
            quizTitle.text = quiestion.title
            quizToolbar.progressStep.text = "${viewModel.step}/${viewModel.questionsSize}"
            val userChoices = mutableListOf<Answer>()
            recyclerAnswers.adapter = AnswersAdapter(answers = quiestion.answers) {
                Paris.style(checkButon).apply(R.style.button_bottom_blue_default)
                checkButon.isEnabled = true
                userChoices.add(it)
            }
            if (quiestion.optional_image_url.isNotEmpty()) {
                if (quiestion.optional_image_url.startsWith("gs:")) {
                    firebaseUtil.loadImage(
                        quizImage,
                        requireContext(),
                        quiestion.optional_image_url
                    )
                } else {
                    quizImage.loadImage(
                        imageUrl = quiestion.optional_image_url,
                        context = requireContext()
                    ) {
                        quizImage.gone()
                    }
                }
            } else {
                quizImage.gone()
            }
            quizImage.visibleOrGone(!quiestion.optional_image_url.isEmpty())
            checkButon.text = getString(R.string.check)
            checkButon.setOnClickListener {
                lifecycleScope.launchWhenResumed {
                    viewModel.checkForCorrect(userChoices).collect { state ->
                        when (state) {
                            is State.DataState -> {
                                val result = state.data
                                showUserChoicesResult(
                                    result = result.result,
                                    userAnswers = result.userChoices,
                                    result.lastQuestion
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}
