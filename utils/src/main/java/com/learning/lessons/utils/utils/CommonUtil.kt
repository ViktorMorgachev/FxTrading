package com.learning.lessons.utils.utils

fun String.toSeconds(): Int {
    val count = this.count { it == ':' }
    when (count) {
        0 -> return 0
        1 -> {
            val minutes = this.substringBefore(":")
            val seconds = this.substringAfterLast(":")
            val result = minutes.toIntOrNull()?.times(60)?.plus(seconds.toIntOrNull() ?: 0) ?: 0
            return result
        }
        2 -> {
            val hours = this.substringBefore(":")
            val minutes = this.substringAfter(":").substringBefore(':')
            val seconds = this.substringAfterLast(":")
            val result = hours.toIntOrNull()?.times(3600)?.plus(minutes.toIntOrNull()?.times(60) ?: 0)?.plus(seconds.toIntOrNull() ?: 0) ?: 0
            return result
        }
    }
    return count
}