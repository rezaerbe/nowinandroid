plugins {
    id("nowinandroid.android.feature")
}

android {
    namespace = "com.erbe.nowinandroid.feature.article"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:design"))

    implementation(project(":data:article"))

    testImplementation(libs.junit)
    testImplementation(libs.androidx.archCore)
    testImplementation(libs.kotlinx.coroutinesTest)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
}