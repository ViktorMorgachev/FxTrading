import com.viktor.buildsrc.app.KaptDependency
import com.viktor.buildsrc.ui.UIDeps
import com.viktor.buildsrc.app.AppDeps

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31
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
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    api(project(":resources"))
    api(project(":core"))
    api(project(":utils"))

    api(UIDeps.core_ktx)
    api(UIDeps.appCompat)
    api(UIDeps.constraintlayout)
    api(UIDeps.android_material)
    api(UIDeps.navigation_ui_ktx)
    api(UIDeps.navigation_fragment_ktx)
    api(UIDeps.navigation_fragment)
    api(UIDeps.viewbindingpropertydelegate)


    api("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    api("androidx.navigation:navigation-fragment-ktx:2.4.1")
    api("androidx.navigation:navigation-ui-ktx:2.4.1")
    api("androidx.appcompat:appcompat:1.4.1")
    api("com.google.android.material:material:1.5.0")
    api("androidx.constraintlayout:constraintlayout:2.1.3")

    api("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.3")
    api("com.github.kirich1409:viewbindingpropertydelegate:1.5.3")

    api("org.jetbrains.kotlinx:kotlinx-datetime:0.2.0")
    api("androidx.legacy:legacy-support-v4:1.0.0")

    kapt(KaptDependency.dagger_android_processor)
    kapt(KaptDependency.dagger_android_compiler)
}