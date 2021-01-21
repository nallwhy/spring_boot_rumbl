import org.springframework.boot.gradle.tasks.bundling.BootJar

buildscript {
    repositories {
        mavenCentral()
        jcenter()
        maven("https://jitpack.io")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-script-util:1.3.20")
        classpath("com.improve_future:harmonica:1.1.26")
    }
}

apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "org.jetbrains.kotlin.plugin.spring")
apply(plugin = "jarmonica")

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    runtimeOnly("org.postgresql:postgresql")
    implementation("com.improve_future:harmonica:1.1.26")
    implementation("org.reflections:reflections:0.9.11")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

extensions.extraProperties["migrationPackage"] = "ee.maytr.rumbl.domain.db"
