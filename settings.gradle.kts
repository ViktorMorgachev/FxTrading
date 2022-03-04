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
include(":domain")
include(":data")
include(":feature_onboarding")
include(":feature_questions")
include(":feature_lesson")
include(":feature_resources")
