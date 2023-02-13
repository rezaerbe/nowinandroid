@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.androidx.navigation) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}