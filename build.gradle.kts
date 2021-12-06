plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

dependencies {
    // Other dependencies.
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.21.0")
}

tasks {
    wrapper {
        gradleVersion = "7.3"
    }
    test {
        useJUnitPlatform()
    }
}
