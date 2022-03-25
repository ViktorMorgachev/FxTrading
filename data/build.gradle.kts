import com.viktor.buildsrc.app.KaptDependency
import com.viktor.buildsrc.app.AppDeps

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
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
        debug {
            isMinifyEnabled = false
        }
        defaultConfig {
            buildConfigField(type = "Boolean", name = "USE_MOCK_DATA", value = "false")
            buildConfigField(type = "String", name = "DOCUMENT_DB_PATH", value = "\"dev\"")
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
dependencies{

    api(project(":domain"))
    implementation(project(":utils"))

    api(platform("com.google.firebase:firebase-bom:29.0.0"))
    api("com.google.firebase:firebase-common-ktx:20.0.0")
    api("com.google.firebase:firebase-firestore-ktx:24.0.1")
    api("com.google.firebase:firebase-analytics-ktx")
    api("com.google.firebase:firebase-storage-ktx")
    api("com.firebaseui:firebase-ui-storage:7.2.0")
    api("com.google.firebase:firebase-database-ktx")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.2.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    kapt(KaptDependency.dagger_android_processor)
    kapt(KaptDependency.dagger_android_compiler)

}