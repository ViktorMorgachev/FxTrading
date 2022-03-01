import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
import com.viktor.buildsrc.app.Dependency as Deps
import com.viktor.buildsrc.app.KaptDependency as KaptDeps

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility =  JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies{
    api(project(":domain"))

    api(platform("com.google.firebase:firebase-bom:29.0.0"))
    api("com.google.firebase:firebase-common-ktx:20.0.0")
    api("com.google.firebase:firebase-firestore-ktx:24.0.1")

    implementation("com.google.dagger:dagger:2.35.1")
    implementation("com.google.dagger:dagger-android-support:2.35.1")
    api(Deps.dagger_android)
    kapt(com.viktor.buildsrc.app.KaptDependency.dagger_android_processor)
    kapt(com.viktor.buildsrc.app.KaptDependency.dagger_android_compiler)

}