package com.fx_trading.lessons.feature_onboarding.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.feature_onboarding.R
import com.fx_trading.navigation.Module
import com.fx_trading.navigation.ModuleBinder
import com.fx_trading.navigation.Router
import com.fx_trading.navigation.params.screens.onboarding.SecondThirdScreenParams
import data.DataStoreHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.fx_trading.lessons.feature_onboarding.databinding.FragmentOnBoardingSecondBinding as Binding


class OnBoardingSecondFragment : BaseFragment<Binding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding =
        Binding::inflate

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var dataStoreHelper: DataStoreHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveIntroWasPassedByUser()

        with(binding) {
            buttonNext.setOnClickListener {
                ModuleBinder.gotoToModule(Module.Questions, requireActivity())
            }
        }
    }

    private fun saveIntroWasPassedByUser() {
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreHelper.introWasPassed(true)
        }
    }
}