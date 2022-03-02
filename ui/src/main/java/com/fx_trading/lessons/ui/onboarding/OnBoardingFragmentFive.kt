package com.fx_trading.lessons.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fx_trading.lessons.ui.R
import com.fx_trading.lessons.ui.databinding.FragmentOnBoardingFiveBinding
import com.fx_trading.lessons.ui.databinding.FragmentOnBoardingFourBinding


class OnBoardingFragmentFive : Fragment(R.layout.fragment_on_boarding_five) {

    private val viewBinding: FragmentOnBoardingFiveBinding by viewBinding(FragmentOnBoardingFiveBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding){
            buttonNext.setOnClickListener {
                // тут уже запускать активность для первого quiz и у него уже отдельный navigation host будет
                requireView().findNavController().navigate(OnBoardingFragmentFiveDirections.actionOnBoardingFragmentFiveToWelcomeFragment())
            }
        }
    }
}