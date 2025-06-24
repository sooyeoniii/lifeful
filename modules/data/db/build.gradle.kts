plugins {
    kotlin("plugin.jpa")
}

dependencies {
    implementation(project(":modules:core:domain"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.linecorp.kotlin-jdsl:jpql-dsl")
    implementation("com.linecorp.kotlin-jdsl:jpql-render")
    implementation("com.linecorp.kotlin-jdsl:spring-data-jpa-support")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    runtimeOnly("com.h2database:h2")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}
