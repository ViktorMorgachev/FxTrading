package com.fx_trading.lessons.feature_questions.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import com.fx_trading.lessons.domain.entities.quiz.Question
import com.fx_trading.lessons.feature_questions.R
import com.fx_trading.lessons.feature_questions.databinding.FragmentQuestionsBinding
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class QuestionsFragment : MvpAppCompatFragment(R.layout.fragment_questions), QuiestionsView {

    @InjectPresenter
    lateinit var quiestionsPresenter: QuiestionsPresenter

    private val binding : FragmentQuestionsBinding by viewBinding(FragmentQuestionsBinding::bind)


    override fun onResume() {
        super.onResume()
        quiestionsPresenter.getLesson(1)
    }

    override fun showQuestion(quiestion: Question) {
        TODO("Not yet implemented")
    }

    override fun showCheckResult() {
        TODO("Not yet implemented")
    }
}