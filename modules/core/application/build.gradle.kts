dependencies {
    implementation(project(":modules:core:domain"))
    implementation(project(":modules:data:db"))
    implementation("org.springframework:spring-tx")
//    implementation(project(mapOf("path" to ":modules:api")))
}
