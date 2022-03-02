import com.viktor.buildsrc.app.KaptDependency as KaptDeps
import com.viktor.buildsrc.ui.UIDeps
import com.viktor.buildsrc.app.AppDeps

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.fx_trading.lessons.fxtrading"
        minSdk = 21
        targetSdk = 31
        versionCode =  1
        versionName = "1.0"
    }
    buildFeatures {
        viewBinding  = true
        dataBinding = true
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

dependencies {

    implementation(project(":core"))
    implementation(project(":ui"))
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(UIDeps.android_material)

    implementation(platform("com.google.firebase:firebase-bom:29.0.0"))
    implementation("com.google.firebase:firebase-common-ktx:20.0.0")
    implementation("com.google.firebase:firebase-firestore-ktx:24.0.1")

    implementation(AppDeps.dagger_android_support)
    kapt(KaptDeps.dagger_android_processor)
    kapt(KaptDeps.dagger_android_compiler)
}