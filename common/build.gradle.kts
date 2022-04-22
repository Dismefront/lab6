plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}