import com.viktor.buildsrc.app.KaptDependency
import com.viktor.buildsrc.app.AppDeps

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies{
    implementation(project(":domain"))
    implementation(project(":core"))

    implementation(platform("com.google.firebase:firebase-bom:29.0.0"))
    implementation("com.google.firebase:firebase-common-ktx:20.0.0")
    implementation("com.google.firebase:firebase-firestore-ktx:24.0.1")

    implementation(AppDeps.dagger)
    implementation(AppDeps.dagger_android_support)
    implementation(AppDeps.dagger_android)
    kapt(KaptDependency.dagger_android_processor)
    kapt(KaptDependency.dagger_android_compiler)

}