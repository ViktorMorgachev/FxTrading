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
import com.fx_trading.navigation.Router
import com.fx_trading.navigation.params.screens.onboarding.FiveStartTestScreenParams
import javax.inject.Inject
import com.fx_trading.lessons.feature_onboarding.databinding.FragmentOnBoardingFiveBinding as Binding


class OnBoardingFiveFragment : BaseFragment<Binding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding =
        Binding::inflate

    @Inject
    lateinit var router: Router

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            buttonFinish.setOnClickListener {
                router.navigate(FiveStartTestScreenParams)
//                requireView().findNavController().navigate(OnBoardingFragmentFiveDirections.actionOnBoardingFragmentFiveToStartTestFragment())
              //  ModuleBinder.gotoToModule(module = Module.Main, activity = this@OnBoardingFragmentFive.requireActivity())
            }
        }
    }
}