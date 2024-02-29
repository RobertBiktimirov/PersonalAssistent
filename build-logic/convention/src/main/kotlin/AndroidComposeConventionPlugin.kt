import dev.susu.personalassistant.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", libs.findLibrary("androidx.activity.compose").get())
                add("implementation", platform(libs.findLibrary("androidx.compose.bom").get()))
                add("implementation", libs.findLibrary("androidx.ui").get())
                add("implementation", libs.findLibrary("androidx.ui.graphics").get())
                add("implementation", libs.findLibrary("androidx.ui.tooling.preview").get())
                add("implementation", libs.findLibrary("androidx.material3").get())
            }
        }
    }
}