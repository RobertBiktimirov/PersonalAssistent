pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PersonalAssistant"
include(":app")
include(":core")
include(":core:ui")
include(":core:navigator")
include(":core:database")
include(":core:utils")
include(":feature")
include(":feature:main")
include(":feature:home")
include(":feature:auth")
