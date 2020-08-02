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

project("rumbl-domain") {
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
}

project("rumbl-web") {
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")

	dependencies {
		api(project(":rumbl-domain"))

		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		testImplementation("org.springframework.boot:spring-boot-starter-test") {
			exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.withType<BootJar> {
		enabled = true
	}
}

tasks.withType<BootJar> {
	enabled = false
}
