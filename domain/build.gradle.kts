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

dependencies {


    implementation(platform("com.google.firebase:firebase-bom:29.0.0"))
    implementation("com.google.firebase:firebase-common-ktx:20.0.0")
    implementation("com.google.firebase:firebase-firestore-ktx:24.0.1")

    api(Deps.dagger_android)
    kapt(KaptDeps.dagger_android_processor)
    kapt(KaptDeps.dagger_android_compiler)
}