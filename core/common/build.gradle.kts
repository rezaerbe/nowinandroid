plugins {
    id("nowinandroid.android.library")
}

android {
    namespace = "com.erbe.nowinandroid.core.common"
}

dependencies {

    implementation(libs.androidx.lifecycle.runtime)
}