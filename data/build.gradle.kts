plugins {
//    id("kmm-library-conventions")
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.8.10"
}

kotlin {
    val ktorVersion = "2.3.0"
    val okioVersion = "3.3.0"

    sourceSets {

        jvm()

        val commonMain by getting {
            dependencies {
                implementation(project(":domain"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("com.squareup.okio:okio:$okioVersion")
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
                implementation(libs.ktor.client.cio)
                implementation("org.jsoup:jsoup:1.15.4")
            }
        }
    }
}
