
pluginManagement {
    repositories {


        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }

        mavenCentral()
        mavenLocal()

        gradlePluginPortal()

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven ( "https://jitpack.io" )
        google()
        mavenCentral()

    }
}

rootProject.name = "ChatAppTask"
include(":app")
 