dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FxTrading"
include(":app")
include(":core")
include(":ui")
include(":core:data")
include(":core:domain")
