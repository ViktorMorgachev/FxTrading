import com.viktor.buildsrc.app.KaptDependency as KaptDeps
import com.viktor.buildsrc.ui.UIDeps

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.learning.lessons.android"
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
        debug {
            isMinifyEnabled = false
        }
        defaultConfig {
          //  buildConfigField(type = "String", name = "DOCUMENT_DB_PATH", value = "\"dev\"")
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

    implementation(project(":feature_main"))
    implementation(project(":utils"))

    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation(UIDeps.android_material)

    kapt(KaptDeps.dagger_android_processor)
    kapt(KaptDeps.dagger_android_compiler)
}