plugins {
    `kotlin-dsl`
    java
    id("java-gradle-plugin")
    id("maven")
}

repositories {
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
    google()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}

tasks.jar {
    from(configurations.compileClasspath.get().filter{
        it.path.contains("fernflower.jar")
    }.map { if (it.isDirectory()) it else zipTree(it) })
}


gradlePlugin {
    plugins {
        create("decomposer"){
            id = "com.github.takahirom.decomposer"
            implementationClass = "com.github.takahirom.decomposer.DecomposerPlugin"
        }
    }
}
