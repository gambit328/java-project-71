import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    application
    jacoco
    checkstyle
    alias(libs.plugins.lombok)
    alias(libs.plugins.versions)
    alias(libs.plugins.shadow)
    alias(libs.plugins.sonarqube)
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass = "hexlet.code.App"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-params
    testImplementation(libs.junit.jupiter.params)

    implementation(libs.commons.lang3)
    implementation(libs.commons.collections4)
    implementation(libs.picocli)
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation(libs.jackson.databind)
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
    implementation(libs.jackson.core)

    testRuntimeOnly(libs.junit.platform.launcher)
}

sonar {
    properties {
        property("sonar.projectKey", "gambit328_java-project-71")
        property("sonar.organization", "gambit328-1")
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = setOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        showStandardStreams = true
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    doLast {
        val jacocoReportHtml = reports.html.outputLocation.get().asFile.resolve("index.html").toURI()
        println("JaCoCo HTML: $jacocoReportHtml")
    }
}
