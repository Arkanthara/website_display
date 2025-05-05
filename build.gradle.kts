plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
  id("org.sonarqube") version "6.0.1.5171"
  id("jacoco")
  id("com.google.cloud.tools.jib") version "3.4.5"
}

group = "ems"
version = "0.0.1-SNAPSHOT"

tasks.bootRun {
    mainClass.set("ems.Application")  // updated location after moving App.java
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

sonar {
  properties {
    property("sonar.projectKey", "website-display_backend")
    property("sonar.organization", "website-display")
    property("sonar.host.url", "https://sonarcloud.io")
    property("sonar.projectName", "Backend")
    property("sonar.exclude", "**/*.py, **/test/**")
    property("sonar.java.source", "21")
    property("sonar.coverage.jacoco.xmlReportPaths", "./build/reports/jacoco/test/jacocoTestReport.xml")
    property("sonar.junit.reportPaths", "./build/test-results/test")
  }
}

tasks.jacocoTestReport {
    dependsOn(tasks.build)
    reports {
        xml.required = true
    }
}
