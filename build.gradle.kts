plugins {
    id("java-platform")
}

subprojects {
    group = "tech.deplant.commons"
    version = "0.11.0-SNAPSHOT"

    repositories {
        mavenLocal()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/deplant/maven-artifactory")
            credentials {
                username = project.properties["githubUsername"] as String?
                password = project.properties["githubUploadToken"] as String?
            }
        }
        mavenCentral()
    }
}