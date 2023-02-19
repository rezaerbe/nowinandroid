package com.erbe.nowinandroid

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

@Suppress("UnstableApiUsage")
internal fun Project.configureView(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.apply {
        buildFeatures {
            viewBinding = true
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        add("implementation", libs.findLibrary("android.material").get())
    }
}