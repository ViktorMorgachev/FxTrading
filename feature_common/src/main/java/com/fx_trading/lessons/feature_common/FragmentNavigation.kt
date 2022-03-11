package com.fx_trading.lessons.feature_common

import androidx.navigation.NavDirections
import com.fx_trading.lessons.feature_common.ui.questions.QuestionsFragmentDirections

import com.fx_trading.lessons.feature_common.ui.questions.pre_result.LastQuestionAnsweredFragmentDirections

fun openLastQuestionsResult(
    firstQuestion: Boolean, questionGroupID: Int, successAnswers: Int,
    totalAnswers: Int, successQuestions: Int, totalQuestions: Int
): NavDirections {
    return  QuestionsFragmentDirections.actionQuestionsFragmentToLastQuestionAnsweredFragment(firstQuestion, questionGroupID, successAnswers, totalAnswers, successQuestions, totalQuestions)
}

fun openTotalUserLevelResult(questionGroupID: Int, successAnswers: Int,
    totalAnswers: Int, successQuestions: Int, totalQuestions: Int
): NavDirections {
   return LastQuestionAnsweredFragmentDirections.actionLastQuestionAnsweredFragmentToTotalUserLevelResultFragment(questionGroupID = questionGroupID, successAnswers = successAnswers,
       successQuestions = successQuestions, totalQuestions = totalQuestions, totalAnswers = totalAnswers)
}