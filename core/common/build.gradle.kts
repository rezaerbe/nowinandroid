plugins {
    id("nowinandroid.android.core")
}

android {
    namespace = "com.erbe.nowinandroid.core.common"
}

dependencies {

    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.moshi)
    kapt(libs.moshi.codegen)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converterMoshi)
    implementation(libs.okhttp)
    implementation(libs.chucker)
}