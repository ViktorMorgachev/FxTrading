package com.fx_trading.lessons.feature_main.feature_common.questions.question_result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.features.databinding.FragmentTotalResultQuestionsAfterLessonBinding

class TotalQuestionsResultFragment : BaseFragment<FragmentTotalResultQuestionsAfterLessonBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTotalResultQuestionsAfterLessonBinding =
        FragmentTotalResultQuestionsAfterLessonBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
