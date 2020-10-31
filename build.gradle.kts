plugins {
    id("org.springframework.boot") version "2.3.1.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.9.RELEASE" apply false
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72" apply false
}

allprojects {
    group = "ee.maytr"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
        jcenter()
        maven("https://jitpack.io")
    }

    tasks {
        compileJava {
            sourceCompatibility = "11"
        }

        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "11"
            }
        }

        compileTestKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "11"
            }
        }
    }
}

subprojects {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-log4j2")
        implementation("io.github.microutils:kotlin-logging:1.12.0")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
    }

    configurations {
        all {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        }
    }
}
