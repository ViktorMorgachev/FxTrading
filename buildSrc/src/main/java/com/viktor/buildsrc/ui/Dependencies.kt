package com.viktor.buildsrc.ui

object Version {
    const val appCompat = "1.4.1"
    const val core_ktx = "1.7.0"
    const val android_material="1.5.0"
    const val constraintlayout = "2.1.3"
    const val nav_component_version = "2.4.1"
    const val navigation_fragment_ktx = nav_component_version
    const val navigation_ui_ktx = nav_component_version
    const val navigation_fragment = nav_component_version
    const val viewbindingpropertydelegate_version = "1.5.3"

}

object UIDeps {
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val core_ktx = "androidx.core:core-ktx:${Version.core_ktx}"
    const val android_material = "com.google.android.material:material:${Version.android_material}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Version.constraintlayout}"
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Version.navigation_fragment_ktx}"
    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Version.navigation_ui_ktx}"
    const val navigation_fragment =  "androidx.navigation:navigation-fragment:${Version.navigation_fragment}"
    const val viewbindingpropertydelegate = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Version.viewbindingpropertydelegate_version}"

}
