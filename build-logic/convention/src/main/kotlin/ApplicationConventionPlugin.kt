import com.android.build.api.dsl.ApplicationExtension
import com.erbe.nowinandroid.configureAndroid
import com.erbe.nowinandroid.configureView
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("nowinandroid.android.fragment")
                apply("nowinandroid.android.hilt")
            }

            extensions.configure<ApplicationExtension> {
                configureAndroid(this)
                configureView(this)
                defaultConfig.targetSdk = 33
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation", libs.findLibrary("androidx.core").get())
                add("implementation", libs.findLibrary("androidx.appcompat").get())
            }
        }
    }
}