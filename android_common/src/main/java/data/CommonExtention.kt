package data

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fx_trading.lessons.utils.utils.Logger
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
fun <T> Flow<T>.launchWhenStarted(lifecycleOwner: LifecycleOwner)= with(lifecycleOwner) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED){
            try {
                this@launchWhenStarted.collect()
            }catch (t: Throwable){
                Logger.log("launchWhenStarted", "Error", t)
            }
        }
    }
}