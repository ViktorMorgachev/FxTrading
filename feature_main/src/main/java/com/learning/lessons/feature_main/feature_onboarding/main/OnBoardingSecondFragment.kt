package com.learning.lessons.feature_main.feature_onboarding.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.feature_main.activities.QuestionActivity
import com.learning.lessons.features.databinding.FragmentOnBoardingSecondBinding
import com.learning.navigation.Router
import data.DataStoreHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class OnBoardingSecondFragment : BaseFragment<FragmentOnBoardingSecondBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOnBoardingSecondBinding =
        FragmentOnBoardingSecondBinding::inflate

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var dataStoreHelper: DataStoreHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveIntroWasPassedByUser()

        with(binding) {
            buttonNext.setOnClickListener {
                startActivity(Intent(requireActivity(), QuestionActivity::class.java))
            }
        }
    }

    private fun saveIntroWasPassedByUser() {
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreHelper.introWasPassed(true)
        }
    }
}