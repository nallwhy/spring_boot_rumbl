import org.springframework.boot.gradle.tasks.bundling.BootJar

apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "org.jetbrains.kotlin.plugin.spring")

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootJar> {
    enabled = false
}

// works
val jar: Jar by tasks
jar.enabled = true

// doesn't work
// Execution failed for task ':rumbl-domain:bootJar'.
// > Main class name has not been configured and it could not be resolved

// tasks.withType<Jar> {
// 	enabled = true
// }
