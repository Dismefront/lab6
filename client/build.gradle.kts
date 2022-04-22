plugins {
    java
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
    mainClass.set("main.Main")
}


tasks.create<Jar>("fatJar") {
    tasks["build"].dependsOn(this)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}