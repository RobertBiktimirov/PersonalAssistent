import dev.susu.personalassistant.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("personalassistant.android.library")
                apply("personalassistant.android.hilt")
                apply("personalassistant.android.compose")
            }
            dependencies {

                add("implementation", project(":core:ui"))
                add("implementation", project(":core:navigator"))
                add("implementation", project(":core:database"))
                add("implementation", libs.findLibrary("gson").get())

                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())

                add("implementation", libs.findLibrary("androidx.room.runtime").get())
                add("ksp", libs.findLibrary("androidx.room.compiler").get())
                add("implementation", libs.findLibrary("androidx.room.ktx").get())
            }
        }
    }
}