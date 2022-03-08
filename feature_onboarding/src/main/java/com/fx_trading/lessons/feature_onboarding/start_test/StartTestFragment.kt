package com.fx_trading.lessons.feature_onboarding.start_test

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fx_trading.lessons.utils.utils.Logger
import com.fx_trading.lessons.feature_onboarding.R
import com.fx_trading.lessons.feature_onboarding.databinding.FragmentStartTestBinding

class StartTestFragment : Fragment(R.layout.fragment_start_test) {

    private val viewBinding: FragmentStartTestBinding by viewBinding(FragmentStartTestBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding){
            skipButton.setOnClickListener {
//                ModuleBinder.gotoToModule(module = Module.Main, activity = this@StartTestFragment.requireActivity())
            }
            startTest.setOnClickListener {
//                ModuleBinder.gotoToModule(module = Module.Questions, activity = this@StartTestFragment.requireActivity())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.log("StartTestFragment", "onDestroy()")
    }

    override fun onPause() {
        super.onPause()
        Logger.log("StartTestFragment", "onPause()")
    }
}