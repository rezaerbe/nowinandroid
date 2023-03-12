plugins {
    id("nowinandroid.android.library")
}

android {
    namespace = "com.erbe.nowinandroid.data.article"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    implementation(project(":core:common"))

    implementation(libs.moshi)
    kapt(libs.moshi.codegen)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converterMoshi)

    testImplementation(libs.junit)
    testImplementation(libs.androidx.archCore)
    testImplementation(libs.kotlinx.coroutinesTest)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
}