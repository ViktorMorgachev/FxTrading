package com.viktor.buildsrc.app

object Version {
  const val dagger_android = "2.35.1"
    const val dagger_android_processor = dagger_android
}

object Dependency {
    val dagger_android = "com.google.dagger:dagger-android:${Version.dagger_android}"

}

object KaptDependency {
    val dagger_android_processor =  "com.google.dagger:dagger-android-processor:${Version.dagger_android_processor}"
}
