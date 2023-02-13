@file:Suppress("UnstableApiUsage")

plugins {
    id("nowinandroid.android.library.view")
}

android {
    namespace = "com.erbe.nowinandroid.core.design"

    lint {
        disable.add("UnusedResources")
    }
}