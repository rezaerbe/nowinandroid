@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "nowinandroid"
include(":app")
include(":core:common")
include(":core:design")
include(":core:firebase")
include(":data:article")
include(":data:podcast")
include(":data:video")
include(":feature:article")