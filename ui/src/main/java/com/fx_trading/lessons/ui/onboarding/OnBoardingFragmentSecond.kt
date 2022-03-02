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
import com.fx_trading.lessons.ui.databinding.FragmentOnBoardingFirstBinding
import com.fx_trading.lessons.ui.databinding.FragmentOnBoardingFirstBindingImpl
import com.fx_trading.lessons.ui.databinding.FragmentOnBoardingSecondBinding


class OnBoardingFragmentSecond : Fragment(R.layout.fragment_on_boarding_second) {
    private val viewBinding: FragmentOnBoardingSecondBinding by viewBinding(FragmentOnBoardingSecondBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding){
            buttonNext.setOnClickListener {
                requireView().findNavController().navigate(OnBoardingFragmentSecondDirections.actionOnBoardingFragmentSecondtoOnBoardingFragmentThird())
            }
        }
    }
}