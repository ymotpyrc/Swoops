val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
//    id("kmm-library-conventions")
    kotlin("multiplatform")
}


kotlin {
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.domain)
                implementation(libs.findLibrary("kotlinx-coroutines-core").get())
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.findLibrary("kotlinx-coroutines-test").get())
            }
        }
    }
}
