import dev.susu.personalassistant.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("personalassistant.android.library")
                apply("personalassistant.android.hilt")
            }
            dependencies {
//                add("implementation", project(":core:ui"))
//                add("implementation", project(":core:designsystem"))

                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())

//                add("implementation",libs.androidx.activity.compose)
//                add("implementation",platform(libs.androidx.compose.bom))
//                add("implementation",libs.androidx.ui)
//                add("implementation",libs.androidx.ui.graphics)
//                add("implementation",libs.androidx.ui.tooling.preview)
//                add("implementation",libs.androidx.material3)


                add("implementation",libs.findLibrary("androidx.activity.compose").get())
                add("implementation",platform(libs.findLibrary("androidx.compose.bom").get()))
                add("implementation",libs.findLibrary("androidx.ui").get())
                add("implementation",libs.findLibrary("androidx.ui.graphics").get())
                add("implementation",libs.findLibrary("androidx.ui.tooling.preview").get())
                add("implementation",libs.findLibrary("androidx.material3").get())
            }
        }
    }
}