package data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.DocumentSnapshot.ServerTimestampBehavior
import com.learning.lessons.utils.utils.Logger
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import java.time.format.TextStyle
import java.util.*

@InternalCoroutinesApi
inline fun <T> Flow<T>.launchWithLifecycle(lifecycleOwner: LifecycleOwner, whenLifecycleState: Lifecycle.State, crossinline action: suspend (value: T) -> Unit)= with(lifecycleOwner) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED){
            try {
                this@launchWithLifecycle.collect(action)
            }catch (t: Throwable){
                Logger.log("launchWithLifecycle", "Error", t)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.formatDate(): String{
    try {
        val instant = Instant.parse(this)
        val javaDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault()).toJavaLocalDateTime()
        return "${javaDateTime.dayOfMonth} ${javaDateTime.month.getDisplayName(TextStyle.FULL, Locale.getDefault())}"
    } catch (e: Exception){
        Logger.log("formatDate()", exception = e)
        return this
    }

}

@RequiresApi(Build.VERSION_CODES.O)
fun String.formatDateTime(): String {
    try {
        val instant = Instant.parse(this)
        val javaDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault()).toJavaLocalDateTime()
        return "${javaDateTime.dayOfMonth} ${javaDateTime.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} @ ${javaDateTime.hour}:${javaDateTime.minute}"
    } catch (e: Exception){
        Logger.log("formatDateTime()", exception = e)
        return this
    }
}

fun String.isFuture(): Boolean{
   return Clock.System.now().toEpochMilliseconds() < Instant.parse(this).toEpochMilliseconds()
}

