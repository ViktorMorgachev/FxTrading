import com.viktor.buildsrc.app.KaptDependency
import com.viktor.buildsrc.ui.Dependency as Deps

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
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
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    api(project(":core"))

    implementation(Deps.core_ktx)
    implementation(Deps.appCompat)
    implementation(Deps.constraintlayout)
    implementation(Deps.android_material)
    implementation(Deps.navigation_ui_ktx)
    implementation(Deps.navigation_fragment_ktx)
    implementation(Deps.navigation_fragment)

    implementation("com.google.dagger:dagger:2.35.1")
    implementation("com.google.dagger:dagger-android-support:2.35.1")
    api(com.viktor.buildsrc.app.Dependency.dagger_android)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(KaptDependency.dagger_android_processor)
    kapt(KaptDependency.dagger_android_compiler)

}