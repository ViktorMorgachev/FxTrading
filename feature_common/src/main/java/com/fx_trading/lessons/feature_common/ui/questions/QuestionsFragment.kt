package com.fx_trading.lessons.feature_common.ui.questions

import by.kirich1409.viewbindingdelegate.viewBinding
import com.fx_trading.lessons.domain.entities.quiz.Question
import com.fx_trading.lessons.feature_common.R
import com.fx_trading.lessons.feature_common.databinding.FragmentQuestionsBinding
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class QuestionsFragment : MvpAppCompatFragment(R.layout.fragment_questions), QuiestionsView {

    @Inject
    @InjectPresenter
    lateinit var questionsPresenter: QuestionsPresenter

    @ProvidePresenter
    fun provide() = questionsPresenter

    private val binding : FragmentQuestionsBinding by viewBinding(FragmentQuestionsBinding::bind)


    override fun onResume() {
        super.onResume()
        questionsPresenter.fetchFirstQuestions()
    }

    override fun showQuestion(quiestion: Question) {
        TODO("Not yet implemented")
    }

    override fun showCheckResult() {
        TODO("Not yet implemented")
    }
}
