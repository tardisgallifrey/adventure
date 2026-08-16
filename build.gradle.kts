plugins {
    id("application")
    java
    jacoco
}

repositories {
    mavenCentral()
}


application {
    mainClass.set("com.tardisgallifrey.adventure.Main")
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        html.required.set(true)
        xml.required.set(true)
        csv.required.set(false)
    }
}

tasks.run.configure{
    standardInput = System.`in`
}

tasks.named<Jar>("jar"){
    manifest {
        attributes["Main-Class"] = "com.tardisgallifrey.adventure.Main"
    }
}

tasks.register<Copy>( "copyJarToRoot" ){
        dependsOn( tasks.named( "jar" )  )
        from( layout.buildDirectory.dir( "libs" )  )
        include( "adventure.jar" )
        into( layout.projectDirectory )
}

tasks.named( "build" ){
        finalizedBy( "copyJarToRoot" )
}

dependencies {
    implementation("org.apache.logging.log4j:log4j-core:2.25.2")
    implementation("com.ironsoftware:ironpdf:2025.11.12")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.junit.platform:junit-platform-launcher:1.13.4")

}

tasks.named<Test>("test"){
    useJUnitPlatform()
}


