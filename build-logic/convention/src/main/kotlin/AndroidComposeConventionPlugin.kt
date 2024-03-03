import com.android.build.gradle.LibraryExtension
import dev.susu.personalassistant.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {

            extensions.configure<LibraryExtension> {
                buildFeatures {
                    compose = true
                }

                composeOptions {
                    kotlinCompilerExtensionVersion = "1.5.1"
                }
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx.activity.compose").get())
                add("implementation", platform(libs.findLibrary("androidx.compose.bom").get()))
                add("implementation", libs.findLibrary("androidx.ui").get())
                add("implementation", libs.findLibrary("androidx.ui.graphics").get())
                add("implementation", libs.findLibrary("androidx.ui.tooling.preview").get())
                add("implementation", libs.findLibrary("androidx.material3").get())
                add("implementation", libs.findLibrary("android.compose.pager").get())

                add("debugImplementation", libs.findLibrary("androidx.ui.tooling").get())
                add(
                    "androidTestImplementation",
                    platform(libs.findLibrary("androidx.compose.bom").get())
                )
            }
        }
    }
}