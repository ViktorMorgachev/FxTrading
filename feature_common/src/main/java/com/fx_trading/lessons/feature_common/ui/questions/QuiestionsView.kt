package com.fx_trading.lessons.feature_common.ui.questions

import com.fx_trading.lessons.domain.entities.quiz.Question
import com.fx_trading.lessons.domain.entities.quiz.QuestionsGroup
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface QuiestionsView: MvpView {
    fun showQuestion(quiestion: Question, questionSize: Int, step: Int, succesCount: Int)
}