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

    api(project(":data"))

    api(com.viktor.buildsrc.ui.UIDeps.core_ktx)
    api(com.viktor.buildsrc.ui.UIDeps.appCompat)
    api(com.viktor.buildsrc.ui.UIDeps.constraintlayout)
    api(com.viktor.buildsrc.ui.UIDeps.android_material)
    api(com.viktor.buildsrc.ui.UIDeps.navigation_ui_ktx)
    api(com.viktor.buildsrc.ui.UIDeps.navigation_fragment_ktx)
    api(com.viktor.buildsrc.ui.UIDeps.navigation_fragment)
    api(com.viktor.buildsrc.ui.UIDeps.viewbindingpropertydelegate)

    api(AppDeps.dagger)
    api(AppDeps.dagger_android_support)
    api(AppDeps.dagger_android)

    kapt(com.viktor.buildsrc.app.KaptDependency.dagger_android_processor)
    kapt(com.viktor.buildsrc.app.KaptDependency.dagger_android_compiler)

}