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
include(":domain")
include(":data")
include(":feature_onboarding")
