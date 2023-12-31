plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

val testngVersion = "7.8.0"
val restAssuredVersion = "5.3.2"
val allureVersion = "2.24.0"
dependencies {
    implementation("io.rest-assured:rest-assured:$restAssuredVersion")
    implementation("io.rest-assured:json-path:$restAssuredVersion")
    testImplementation("io.rest-assured:kotlin-extensions:$restAssuredVersion")
    testImplementation("org.testng:testng:$testngVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")

    implementation("io.qameta.allure:allure-rest-assured:$allureVersion")
    testImplementation("io.qameta.allure:allure-testng:$allureVersion")
    testImplementation("org.aspectj:aspectjweaver:1.9.5")
    testImplementation("io.qameta.allure:allure-assertj:$allureVersion")
    testImplementation("io.qameta.allure:allure-java-commons:$allureVersion")

    testImplementation("org.slf4j:slf4j-simple:2.0.9")
    implementation("com.typesafe:config:1.4.2")
    testImplementation("junit:junit:4.13.1")
}

tasks.test {
    useTestNG()
}