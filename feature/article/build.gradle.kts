plugins {
    id("nowinandroid.android.feature")
}

android {
    namespace = "com.erbe.nowinandroid.feature.article"
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:design"))

    implementation(project(":data:article"))
}