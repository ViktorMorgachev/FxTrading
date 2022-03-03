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


    implementation(platform("com.google.firebase:firebase-bom:29.0.0"))
    implementation("com.google.firebase:firebase-common-ktx:20.0.0")
    implementation("com.google.firebase:firebase-firestore-ktx:24.0.1")

    implementation("com.google.dagger:dagger:2.35.1")

    api(AppDeps.kotlin_coroutunes_core)
    api(AppDeps.kotlin_coroutunes_android)

    api(AppDeps.dagger_android)
    kapt(KaptDeps.dagger_android_processor)
    kapt(KaptDeps.dagger_android_compiler)
}