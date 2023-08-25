plugins {
    id("kmm-library-conventions")
//    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.8.10"
}

kotlin {
    val okioVersion = "3.3.0"

    sourceSets {
        jvm()
        val commonMain by getting {
            dependencies {
                implementation(project(":domain"))
                implementation(project(":presentation"))
                implementation(project(":data"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("com.squareup.okio:okio-fakefilesystem:$okioVersion")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-html-builder-jvm:2.0.2")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
            }
        }
    }
}
