plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"
    kotlin("plugin.jpa") version "2.0.0"
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.jlleitschuh.gradle.ktlint") version "12.3.0"
}


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.bootJar {
    enabled = false
}
tasks.jar {
    enabled = true
}

allprojects {
    group = "lifeful"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    val dependencyGroups = mapOf(
        "org.springdoc" to "2.8.8",
        "io.kotest" to "5.9.0",
        "io.github.oshai" to "7.0.7",
        "com.linecorp.kotlin-jdsl" to "3.5.5",
    )

    configurations.all {
        resolutionStrategy.eachDependency {
            dependencyGroups[requested.group]?.also { constraintVersion ->
                useVersion(constraintVersion)
                because("custom dependency group")
            }
        }
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2025.0.0")
        }
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("io.github.oshai:kotlin-logging-jvm")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testImplementation("io.kotest:kotest-runner-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

ktlint {
    android.set(false)
    outputToConsole.set(true)
    enableExperimentalRules.set(false)
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        apiVersion = "2.0"
        languageVersion = "2.0"
    }
}
