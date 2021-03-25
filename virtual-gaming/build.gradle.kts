import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.30"
	kotlin("plugin.spring") version "1.4.30"
	kotlin("plugin.jpa") version "1.4.30"
	kotlin("plugin.allopen") version "1.4.30"
	id("org.jetbrains.dokka") version "1.4.30"
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

group = "com.salesianostriana.dam"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	jcenter()
}

dependencies {
	//Lo usamos para definir las entidades y los tipo de relacion entre clases
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	//Lo usamos para la securidad y la aplicacion tenga un login
	implementation("org.springframework.boot:spring-boot-starter-security")
	//Usado para definir una aplicacion web
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	//Usado para la validacion de algunos parametros de clases
	implementation("org.springframework.boot:spring-boot-starter-validation")
	//Para obtener una documentacion de las distintas consultas en los controladores
	implementation("io.springfox:springfox-swagger2:2.7.0")
	implementation("io.springfox:springfox-swagger-ui:2.7.0")
	//Lo usamos para la implementacion del token y sus diversos usos
	implementation (group= "io.jsonwebtoken", name= "jjwt-api", version="0.11.2")
	implementation (group= "io.jsonwebtoken", name= "jjwt-impl", version="0.11.2")
	implementation (group= "io.jsonwebtoken", name= "jjwt-jackson", version="0.11.2")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.4.30")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
