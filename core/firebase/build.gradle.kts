plugins {
    id("nowinandroid.android.library")
}

android {
    namespace = "com.erbe.nowinandroid.core.firebase"
}

dependencies {

    implementation(platform("com.google.firebase:firebase-bom:31.2.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
}