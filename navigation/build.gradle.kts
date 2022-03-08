import com.viktor.buildsrc.app.AppDeps
import com.viktor.buildsrc.ui.UIDeps

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

dependencies {
    api(UIDeps.navigation_fragment_ktx)
    api(UIDeps.navigation_ui_ktx)

    api(AppDeps.dagger)
    api(AppDeps.dagger_android_support)
    api(AppDeps.dagger_android)

    kapt(com.viktor.buildsrc.app.KaptDependency.dagger_android_processor)
    kapt(com.viktor.buildsrc.app.KaptDependency.dagger_android_compiler)
}