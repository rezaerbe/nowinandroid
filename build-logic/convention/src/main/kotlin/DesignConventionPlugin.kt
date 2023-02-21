import com.android.build.api.dsl.LibraryExtension
import com.erbe.nowinandroid.configureAndroid
import com.erbe.nowinandroid.configureView
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class DesignConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureAndroid(this)
                configureView(this)
            }
        }
    }
}