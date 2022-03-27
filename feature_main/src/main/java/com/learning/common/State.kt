package com.learning.common

sealed class State<out T> {
    object LoadingState : State<Nothing>()
    data class ErrorState(var exception: Throwable) : State<Nothing>()
    data class DataState<T>(var data: T) : State<T>()
    object EmptyState: State<Nothing>()
}

fun <T> createState(data: T?): State<T> {
    return if (data == null || (data is List<*> && data.isEmpty())) State.EmptyState else State.DataState<T>(data)
}