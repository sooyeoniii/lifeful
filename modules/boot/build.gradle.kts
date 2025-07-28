dependencies {
    implementation(project(":modules:api"))
    implementation(project(":modules:data:db"))
    implementation(project(":modules:support"))
}

springBoot {
    mainClass.set("lifeful.LifefulApplication")
}

tasks.bootJar {
    enabled = true
}
