package com.viktor.buildsrc.app

import com.viktor.buildsrc.app.Version.dagger_android


object Version {
  const val dagger_android = "2.41"
    const val dagger_android_processor = dagger_android
}

object AppDeps {
    val dagger = "com.google.dagger:dagger:2.35.1"
    val dagger_android = "com.google.dagger:dagger-android:${Version.dagger_android}"
    val dagger_android_support = "com.google.dagger:dagger-android-support:2.35.1"
    val kotlin_coroutunes_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1"
    val kotlin_coroutunes_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2"
    val moxy = "com.arello-mobile:moxy:1.4.5"
    val moxy_android = "com.arello-mobile:moxy-android:1.4.5"
}

object KaptDependency {
    val dagger_android_processor =  "com.google.dagger:dagger-android-processor:$dagger_android"
    val dagger_android_compiler = "com.google.dagger:dagger-compiler:$dagger_android"
    val moxy_compiler = "com.arello-mobile:moxy-compiler:1.4.5'"

}
