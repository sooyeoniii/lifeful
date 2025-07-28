dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    api("com.linecorp.kotlin-jdsl:jpql-dsl")
    implementation("com.linecorp.kotlin-jdsl:jpql-render")
    implementation("com.linecorp.kotlin-jdsl:spring-data-jpa-support")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}