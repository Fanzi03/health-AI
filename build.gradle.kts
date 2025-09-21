val lombokVersion = "1.18.38"

plugins {
	id("org.springframework.boot") version "3.5.6"
	id("io.spring.dependency-management") version "1.1.7"
	id("info.solidsoft.pitest") version "1.19.0-rc.1"
	application
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	compileOnly("org.projectlombok:lombok:$lombokVersion")
	annotationProcessor("org.projectlombok:lombok:$lombokVersion")
	testCompileOnly("org.projectlombok:lombok:$lombokVersion")
	testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")

	testImplementation(libs.junit.jupiter)
	testImplementation("io.rest-assured:rest-assured:5.5.6")
	testImplementation("com.arcmutate:arcmutate-spring:1.1.1")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation(libs.guava)
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

application {
	mainClass = "org.ai.App"
}

tasks.named<Test>("test") {
	useJUnitPlatform()
}

pitest {
	    junit5PluginVersion.set("1.2.3")
	    targetClasses.set(listOf(
		    "org.ai.*",
    	    ))
//	    excludedClasses.set(listOf(
//		"org.application.App*",
//		"org.application.config.*",
//		"org.application.entity.*",
//		"org.application.**.*DataTransferObject*",
//		"org.application.controllers.HomeController*"
//	    ))

	    threads.set(5) 

	    mutationThreshold.set(80)
	    coverageThreshold.set(80)
	    outputFormats.set(listOf("XML", "HTML"))
	    timestampedReports.set(false)

}

