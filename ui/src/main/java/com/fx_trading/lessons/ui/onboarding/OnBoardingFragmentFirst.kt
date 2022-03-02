package com.fx_trading.lessons.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.fx_trading.lessons.ui.R
import com.fx_trading.lessons.ui.splash.SplashFragmentDirections


class OnBoardingFragmentFirst : Fragment() {

    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view  =  inflater.inflate(R.layout.fragment_on_boarding_first, container, false)
        button =  view.findViewById<Button>(R.id.button)
        return view
    }

    override fun onResume() {
        super.onResume()
        button?.setOnClickListener {
            requireView().findNavController().navigate(OnBoardingFragmentFirstDirections.actionOnBoardingFragmentFirstToOnBoardingFragmentSecond())
        }
    }
}