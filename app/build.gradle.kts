import com.viktor.buildsrc.app.KaptDependency as KaptDeps
import com.viktor.buildsrc.ui.UIDeps

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
    implementation(project(":resources"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":feature_onboarding"))
    implementation(project(":feature_main"))
    implementation(project(":feature_common"))

    implementation(UIDeps.android_material)

    kapt(KaptDeps.dagger_android_processor)
    kapt(KaptDeps.dagger_android_compiler)
}