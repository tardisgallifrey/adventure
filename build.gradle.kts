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
    reports {
        html.required.set(true)
        xml.required.set(false)
        csv.required.set(false)
    }
}

abstract class showVersion : DefaultTask( ){
         @get:Input
         abstract val version: Property<String>

         @TaskAction
         fun printVersion( ){
                 println( "Project version: ${ version.get( )}" )
         }
}

val versionProvider: Provider<String> = project.provider{ "0.0.5" }

tasks.register( "showVersion", showVersion::class ){
        version = versionProvider
}

//  The simple method works when no variables involved
//  showVersion didn't like the variables
//  The above is how gradle docs show to do it.
//  But, will try a different way soon.
tasks.register( "hello" ){
        doLast{
                println( "Hello from Adventure game!" )
        }
}
 

tasks.run.configure{
    standardInput = System.`in`
}

tasks.named<Jar>("jar"){
    manifest {
        attributes["Main-Class"] = "com.tardisgallifrey.adventure.Main"
    }
    finalizedBy( "copyJarToRoot" ) 
}

abstract class CopyJarToRoot : DefaultTask( ) {

        @get:InputFile
        abstract val jarFile: RegularFileProperty

        @get:OutputFile
        abstract val destination: RegularFileProperty

        @TaskAction
        fun copyJar( ){
                 jarFile.get( ).asFile.copyTo( 
                        destination.get( ).asFile,
                        overwrite = true
                )
        }
}



tasks.register<CopyJarToRoot>( "copyJarToRoot" ){
                 jarFile.set( tasks.named<Jar>( "jar" ).flatMap { it.archiveFile } )
                 destination.set( layout.projectDirectory.file( "adventure.jar" ) ) 
}


dependencies {
    implementation("org.apache.logging.log4j:log4j-core:2.25.2")
    implementation("com.ironsoftware:ironpdf:2025.11.12")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.junit.platform:junit-platform-launcher:1.13.4")

}



