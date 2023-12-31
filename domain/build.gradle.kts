
val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
//    id("kmm-library-conventions")
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.8.10"
}

kotlin {
    sourceSets {
        jvm()
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                // This should go in kmm-library-conventions
                implementation(libs.findLibrary("kotlinx-coroutines-core").get())
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
