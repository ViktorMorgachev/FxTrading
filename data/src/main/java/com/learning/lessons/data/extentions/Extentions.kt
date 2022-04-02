package com.learning.lessons.data.extentions

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.learning.lessons.utils.utils.Logger
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException


@ExperimentalCoroutinesApi
@Throws(Exception::class)
public suspend fun <T> Task<T>.await(): T? {
    if (isComplete) {
        val e = exception
        return if (e == null) {
            if (isCanceled) {
                throw CancellationException(
                    "Task $this was cancelled normally."
                )
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


inline fun <reified T : Any> HashMap<String, Any?>.getField(fieldName: String, default: T): T {
    if (!this.containsKey(fieldName) || this[fieldName] !is T) return default
    return if (this[fieldName] == null) default
    else this[fieldName] as T
}

inline fun <reified T : Any> HashMap<String, Any?>.getField(fieldName: String): T? {
    if (!this.containsKey(fieldName) || this[fieldName] !is T) return null
    return this[fieldName] as T?
}


fun <T> DocumentSnapshot.toObjectOrDefault(
    valueType: Class<T>,
    defaultValue: T? = null,
    logger: Logger = Logger
): T? {
    return try {
        toObject(valueType, DocumentSnapshot.ServerTimestampBehavior.NONE)
    } catch (e: Throwable) {
        logger.log("DocumentSnapshot.toObject()", exception = e)
        defaultValue
    }
}

fun DocumentSnapshot.containsAll(fieldValues: List<Pair<String, Any>>): Boolean {
    Logger.log("DocumentSnapshot.containsAll", "fieldValues $fieldValues")
    fieldValues.forEach { pair ->
        if (this.contains(pair.first)) {
            Logger.log("DocumentSnapshot.containsAll", "fieldValueFromDb ${get(pair.first)}")
            val dataFromFirebase = get(pair.first)
            if (dataFromFirebase is ArrayList<*>) {
                val dataArray = pair.second as ArrayList<*>
                if (dataFromFirebase.size > 0) {
                    // Так как Long и Int нельзя сравнивать
                   if (dataFromFirebase.firstOrNull() !is Long){
                       if(dataFromFirebase != dataArray)  return false
                    } else {
                       if(!(dataFromFirebase.map { (it as Long).toInt() }.equals(dataArray.map { it}))) return false
                    }
                }
            } else {
                if (dataFromFirebase !is Long){
                    if (dataFromFirebase!! != pair.second) return false
                } else {
                    if (dataFromFirebase.toInt() != pair.second) return false
                }

            }
        } else return false
    }
    return true
}

