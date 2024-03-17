plugins {
    alias(libs.plugins.personalassistant.android.libraty)
    alias(libs.plugins.personalassistant.android.hilt)
}

android {
    namespace = "dev.susu.personalassistant.core.database"
}
dependencies {
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
}