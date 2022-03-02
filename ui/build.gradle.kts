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

    api(project(":core"))

    implementation(UIDeps.core_ktx)
    implementation(UIDeps.appCompat)
    implementation(UIDeps.constraintlayout)
    implementation(UIDeps.android_material)
    implementation(UIDeps.navigation_ui_ktx)
    implementation(UIDeps.navigation_fragment_ktx)
    implementation(UIDeps.navigation_fragment)
    implementation(UIDeps.viewbindingpropertydelegate)

    implementation(AppDeps.dagger)
    implementation(AppDeps.dagger_android_support)
    api(AppDeps.dagger_android)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    kapt(KaptDependency.dagger_android_processor)
    kapt(KaptDependency.dagger_android_compiler)

}