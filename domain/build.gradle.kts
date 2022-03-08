import com.viktor.buildsrc.app.AppDeps
import com.viktor.buildsrc.app.KaptDependency as KaptDeps

plugins {
    id("java-library")
    id("kotlin")
    id("kotlin-kapt")

}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    api(AppDeps.kotlin_coroutunes_core)
    api(AppDeps.kotlin_coroutunes_android)
    api(AppDeps.dagger_android)


    kapt(KaptDeps.dagger_android_processor)
    kapt(KaptDeps.dagger_android_compiler)
}