plugins {
//    id("kmm-library-conventions")
    kotlin("multiplatform")
}


kotlin {
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.domain)
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
