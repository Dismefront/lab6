plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    implementation(project(":common"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}
application {
    mainClass.set("Main")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
}


tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}