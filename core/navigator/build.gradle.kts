plugins {
    alias(libs.plugins.personalassistant.android.libraty)
    alias(libs.plugins.personalassistant.android.compose)
    alias(libs.plugins.personalassistant.android.hilt)
}

android {
    namespace = "dev.susu.personalassistant.core.navigator"
}
dependencies {
    implementation(libs.androidx.navigation.common.ktx)
}
