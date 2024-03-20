plugins {
    alias(libs.plugins.personalassistant.android.feature)
}
android {
    namespace = "dev.susu.personalassistant.home"
}
dependencies {
    implementation(libs.androidx.animation.graphics.android)
}
