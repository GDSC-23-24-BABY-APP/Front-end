pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven{
            url = uri("https://maven.google.com")

        }
        jcenter()
        maven{url = uri("https://maven.fabric.io/public") }
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{
            url = uri("https://maven.google.com")
            url = uri("https://devrepo.kakao.com/nexus/content/groups/public/")
        }
        jcenter()
        maven{url = uri("https://maven.fabric.io/public") }
    }
}

rootProject.name = "TOBEMOM"
include(":app")
 