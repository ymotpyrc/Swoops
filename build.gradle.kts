plugins {
//    id("app.cash.sqldelight") version "2.0.0"
}

buildscript {

    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        classpath(libs.kotlin.gradlePlugin)
    }
}

//sqldelight {
//    databases {
//        create("Database") {
//            packageName.set("com.ymotpyrc.swoops")
//        }
//    }
//}

tasks {

}

