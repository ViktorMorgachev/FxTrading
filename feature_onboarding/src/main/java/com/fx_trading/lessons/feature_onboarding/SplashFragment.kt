package com.fx_trading.lessons.feature_onboarding
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fx_trading.lessons.feature_onboarding.databinding.FragmentSplashBinding


class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewBinding: FragmentSplashBinding by viewBinding(FragmentSplashBinding::bind)

    override fun onResume() {
        super.onResume()
        requireView().findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnBoardingFragmentFirst())
    }
}