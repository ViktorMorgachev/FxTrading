package com.fx_trading.lessons.feature_common.ui.questions

import com.fx_trading.lessons.domain.entities.quiz.Question

interface QuiestionsView {
    fun showQuestion(quiestion: Question, questionSize: Int, step: Int, succesCount: Int)
}