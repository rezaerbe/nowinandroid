plugins {
    id("nowinandroid.android.library")
}

android {
    namespace = "com.erbe.nowinandroid.data.article"
}

dependencies {

    implementation(project(":core:common"))

    implementation(libs.moshi)
    kapt(libs.moshi.codegen)
    implementation(libs.retrofit)
}