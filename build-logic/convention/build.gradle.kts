plugins {
    `kotlin-dsl`
}

group = "com.erbe.nowinandroid.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "nowinandroid.android.application"
            implementationClass = "ApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "nowinandroid.android.library"
            implementationClass = "LibraryConventionPlugin"
        }
        register("androidCore") {
            id = "nowinandroid.android.core"
            implementationClass = "CoreConventionPlugin"
        }
        register("androidDesign") {
            id = "nowinandroid.android.design"
            implementationClass = "DesignConventionPlugin"
        }
        register("androidFeature") {
            id = "nowinandroid.android.feature"
            implementationClass = "FeatureConventionPlugin"
        }
        register("androidFragment") {
            id = "nowinandroid.android.fragment"
            implementationClass = "FragmentConventionPlugin"
        }
        register("androidHilt") {
            id = "nowinandroid.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }
    }
}