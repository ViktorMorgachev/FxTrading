package data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fx_trading.lessons.utils.utils.Logger
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import java.time.format.TextStyle
import java.util.*

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

@RequiresApi(Build.VERSION_CODES.O)
fun String.formatDate(pattern: String = "dd MMMM"): String{
   val instant = Instant.parse(this)
    val javaDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault()).toJavaLocalDateTime()
    return "${javaDateTime.dayOfMonth} ${javaDateTime.month.getDisplayName(TextStyle.FULL, Locale.getDefault())}"
}

fun String.isFuture(): Boolean{
   return Clock.System.now().toEpochMilliseconds() < Instant.parse(this).toEpochMilliseconds()
}