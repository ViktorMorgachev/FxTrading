package com.fx_trading.lessons.feature_main.feature_onboarding.start_test

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.feature_main.activities.MainActivity
import com.fx_trading.lessons.feature_main.activities.QuestionActivity
import com.fx_trading.lessons.features.databinding.FragmentStartTestBinding
import com.fx_trading.navigation.Router
import javax.inject.Inject


class StartTestFragment : BaseFragment<FragmentStartTestBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStartTestBinding = FragmentStartTestBinding::inflate

    @Inject
    lateinit var router: Router

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            skipButton.setOnClickListener {
                startActivity(Intent(requireActivity(), MainActivity::class.java))
            }
            startTest.setOnClickListener {
                startActivity(Intent(requireActivity(), QuestionActivity::class.java))
            }
        }
    }
}