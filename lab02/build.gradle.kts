plugins {
    id("java")
    id("application")
}
application {
    mainClass = "org.example.Main"
}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.jar {
    manifest {
        attributes(mapOf("Main-Class" to application.mainClass))
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/com.google.guava/guava
    implementation("com.google.guava:guava:33.4.0-jre")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed")
    }
}