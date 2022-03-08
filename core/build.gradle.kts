import com.viktor.buildsrc.app.AppDeps

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

    buildFeatures {
        viewBinding = true
    }
}

dependencies{
    api(project(":domain"))
    api(project(":navigation"))

    api(platform("com.google.firebase:firebase-bom:29.0.0"))
    api("com.google.firebase:firebase-common-ktx:20.0.0")
    api("com.google.firebase:firebase-firestore-ktx:24.0.1")


    api(AppDeps.dagger)
    api(AppDeps.dagger_android_support)
    api(AppDeps.dagger_android)

    kapt(com.viktor.buildsrc.app.KaptDependency.dagger_android_processor)
    kapt(com.viktor.buildsrc.app.KaptDependency.dagger_android_compiler)

}