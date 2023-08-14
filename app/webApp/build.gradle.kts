//plugins {
////    id("kmm-library-conventions")
//    kotlin("multiplatform")
//    application
//    kotlin("plugin.serialization") version "1.8.10"
//}
//
//group = "me.jfaub"
//version = "1.0-SNAPSHOT"
//
//repositories {
//    mavenCentral()
//    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
//}
//
//kotlin {
//    val ktorVersion = "2.3.0"
//
//    js(IR) {
//        binaries.executable()
//        browser {
//            commonWebpackConfig {
//                cssSupport {
//                    enabled.set(true)
//                }
//            }
//        }
//    }
//    sourceSets {
//        val commonMain by getting {
//            dependencies {
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
//                implementation("io.ktor:ktor-client-core:$ktorVersion")
//            }
//        }
//        val jsMain by getting {
//            dependencies {
//                implementation(project(":domain"))
//                implementation("io.ktor:ktor-client-js:$ktorVersion")
//                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
//                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
//                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.2.0-pre.346")
//                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.2.0-pre.346")
//                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.9.3-pre.346")
//            }
//        }
//    }
//}
//
////application {
////    mainClass.set("me.jfaub.application.ServerKt")
////}
//
////// include JS artifacts in any JAR we generate
////tasks.named<Jar>("jvmJar") {
////    val taskName = if (project.hasProperty("isProduction")
////        || project.gradle.startParameter.taskNames.contains("installDist")
////    ) {
////        "jsBrowserProductionWebpack"
////    } else {
////        "jsBrowserDevelopmentWebpack"
////    }
////    val webpackTask = tasks.getByName<org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack>(taskName)
////    dependsOn(webpackTask) // make sure JS gets compiled first
////    from(File(webpackTask.destinationDirectory, webpackTask.outputFileName)) // bring output file along into the JAR
////}
//
////tasks.named<Copy>("jvmProcessResources") {
////    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
////    from(jsBrowserDistribution)
////}
////
////tasks.named<JavaExec>("run") {
////    dependsOn(tasks.named<Jar>("jvmJar"))
////    classpath(tasks.named<Jar>("jvmJar"))
////}
