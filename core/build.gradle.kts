import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
import com.viktor.buildsrc.app.Dependency as Deps
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

dependencies{
    implementation(project(":core:data"))
    implementation(project(":core:domain"))

    api(platform("com.google.firebase:firebase-bom:29.0.0"))
    api("com.google.firebase:firebase-common-ktx:20.0.0")
    api("com.google.firebase:firebase-firestore-ktx:24.0.1")

    api(com.viktor.buildsrc.app.Dependency.dagger_android)
    kapt(com.viktor.buildsrc.app.KaptDependency.dagger_android_processor)
    kapt(com.viktor.buildsrc.app.KaptDependency.dagger_android_compiler)

}