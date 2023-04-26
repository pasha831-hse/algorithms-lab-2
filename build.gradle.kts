plugins {
    id("java")
    application
}

application {
    mainClass.set("org.example.Main")
    applicationDefaultJvmArgs = listOf("-Xint")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}