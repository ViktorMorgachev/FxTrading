import com.viktor.buildsrc.ui.Dependency as Deps

plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32
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
    implementation(Deps.core_ktx)
    implementation(Deps.appCompat)
    implementation(Deps.constraintlayout)
    implementation(Deps.android_material)
    implementation(Deps.navigation_ui_ktx)
    implementation(Deps.navigation_fragment_ktx)
    implementation(Deps.navigation_fragment)
}