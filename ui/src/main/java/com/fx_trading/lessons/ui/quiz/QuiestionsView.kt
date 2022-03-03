package com.fx_trading.lessons.ui.quiz

import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.domain.entities.quiz.Question
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface QuiestionsView: MvpView {
    fun showQuestion(quiestion: Question)
    fun showCheckResult()
}