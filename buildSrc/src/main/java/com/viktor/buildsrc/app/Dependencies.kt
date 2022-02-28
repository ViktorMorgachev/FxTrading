package com.viktor.buildsrc.app

import com.viktor.buildsrc.app.Version.dagger_android


object Version {
  const val dagger_android = "2.41"
    const val dagger_android_processor = dagger_android
}

object Dependency {
    val dagger_android = "com.google.dagger:dagger-android:${Version.dagger_android}"

}

object KaptDependency {
    val dagger_android_processor =  "com.google.dagger:dagger-android-processor:2.41"
    val dagger_android_compiler = "com.google.dagger:dagger-compiler:2.41"
}
