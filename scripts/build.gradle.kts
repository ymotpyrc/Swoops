
val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    kotlin("jvm")
    application
}

application {
    mainClass.set("com.ymotpyrc.swoops.scripts.MainKt")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(libs.findLibrary("kotlinx-coroutines-core").get())
}
