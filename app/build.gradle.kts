@file:Suppress("UnstableApiUsage")

plugins {
    id("com.google.firebase.crashlytics")
    id("nowinandroid.android.application")
}

android {
    namespace = "com.erbe.nowinandroid"

    defaultConfig {
        applicationId = "com.erbe.nowinandroid"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "env"

    productFlavors {
        create("dev") {
            dimension = "env"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-DEV"
            buildConfigField("String", "FLAVOR", "\"dev\"")
        }
        create("staging") {
            dimension = "env"
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-STAGING"
            buildConfigField("String", "FLAVOR", "\"staging\"")
        }
        create("prod") {
            dimension = "env"
            buildConfigField("String", "FLAVOR", "\"prod\"")
        }
    }

    androidComponents {
        beforeVariants { variantBuilder ->
            if (variantBuilder.name == "devRelease" ||
                variantBuilder.name == "stagingRelease"
            ) {
                variantBuilder.enable = false
            }
        }
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:design"))

    implementation(project(":feature:article"))

    implementation(platform("com.google.firebase:firebase-bom:31.2.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
}

kapt {
    correctErrorTypes = true
}