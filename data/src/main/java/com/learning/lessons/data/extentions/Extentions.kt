package com.learning.lessons.data.extentions

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException
import kotlin.jvm.Throws


@ExperimentalCoroutinesApi
@Throws(Exception::class)
public suspend fun <T> Task<T>.await(): T? {
    if (isComplete) {
        val e = exception
        return if (e == null) {
            if (isCanceled) {
                throw CancellationException(
                    "Task $this was cancelled normally.")
            } else {
                result
            }
        } else {
            throw e
        }
    }

    return suspendCancellableCoroutine { cont ->
        addOnCompleteListener {
            val e = exception
            if (e == null) {
                if (isCanceled) cont.cancel() else cont.resumeWith(Result.success(result))
            } else {
                cont.resumeWithException(e)
            }
        }
    }
}


inline fun <reified T: Any> HashMap<String, Any?>.getField(fieldName: String, default: T): T {
    if (!this.containsKey(fieldName) || this[fieldName] !is T) return default
    return if (this[fieldName] == null) default
    else this[fieldName] as T
}
inline fun <reified T: Any> HashMap<String, Any?>.getField(fieldName: String): T? {
    if (!this.containsKey(fieldName) || this[fieldName] !is T) return null
    return this[fieldName] as T?
}

