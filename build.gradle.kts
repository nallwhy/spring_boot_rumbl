import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

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
	}

	tasks.withType<JavaCompile> {
		sourceCompatibility = "11"
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "11"
		}
	}
}

tasks.withType<BootJar> {
	enabled = false
}
