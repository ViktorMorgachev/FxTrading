package com.fx_trading.lessons.feature_onboarding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.navigation.Router
import com.fx_trading.navigation.params.screens.onboarding.SplashFirstScreenParams
import javax.inject.Inject
import com.fx_trading.lessons.feature_onboarding.databinding.FragmentSplashBinding as Binding


class SplashFragment : BaseFragment<Binding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding = Binding::inflate

    @Inject
    lateinit var router: Router

    override fun onResume() {
        super.onResume()
        router.navigate(SplashFirstScreenParams)
    }
}