@file:Suppress("UnstableApiUsage")

plugins {
    id("nowinandroid.android.design")
}

android {
    namespace = "com.erbe.nowinandroid.core.design"

    lint {
        disable.add("UnusedResources")
    }
}

dependencies {

    implementation(libs.coil)
}