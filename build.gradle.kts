plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.9.22"
    id("com.google.cloud.tools.jib") version "3.4.1"
    application
}

group = "io.kerno"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
    implementation("com.google.cloud.tools.jib:com.google.cloud.tools.jib.gradle.plugin:3.3.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

jib {
    from {
        image = "eclipse-temurin:17-jre"
    }
    to {
        image = "yanmanatkerno/github-poc-pt2"
        tags = setOf(
            System.getenv("GITHUB_SHA")
        )
    }
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
