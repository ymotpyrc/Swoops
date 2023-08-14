rootProject.name = "Swoops"

pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":domain")
include(":data")
include(":backend")
include(":presentation")
include(":ui")
include(":app:desktopApp", ":app:webApp")
