val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    kotlin("multiplatform")
}

kotlin {
//    ios()
//    iosSimulatorArm64()

    jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.findLibrary("kotlinx-coroutines-core").get())
                implementation(libs.findLibrary("koin-core").get())
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.findLibrary("kotlin-test").get())
                implementation(libs.findLibrary("kotlinx-coroutines-test").get())
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(libs.findLibrary("mockk").get())
            }
        }
    }
}
