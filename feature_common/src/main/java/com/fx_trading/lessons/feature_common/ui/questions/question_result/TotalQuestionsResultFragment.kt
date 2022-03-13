package com.fx_trading.lessons.feature_common.ui.questions.question_result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.feature_common.databinding.FragmentTotalResultQuestionsAfterLessonBinding
import com.fx_trading.lessons.feature_common.ui.questions.*
import javax.inject.Inject

class TotalQuestionsResultFragment : BaseFragment<FragmentTotalResultQuestionsAfterLessonBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTotalResultQuestionsAfterLessonBinding =
        FragmentTotalResultQuestionsAfterLessonBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
