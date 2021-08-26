package com.github.takahirom.decomposer

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.kotlin.dsl.withType
import org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

class DecomposerPlugin:Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.withType<KotlinCompile>()
            .whenTaskAdded {
                val kotlinCompileTask: KotlinCompile = this
                this.doLast {
                    val kotlinFiles = kotlinCompileTask.destinationDirectory.asFileTree.files
                        .map{it.absolutePath}
                    val output = File(project.buildDir,"decompiled").apply {
                        deleteRecursively()
                        mkdir()
                    }
                    val options: MutableList<String> = kotlinFiles.toMutableList()
                        .apply{add(output.absolutePath)}
                    ConsoleDecompiler.main(options.toTypedArray())
                    output.listFiles()
                        ?.filter { !it.readText().contains("androidx.compose") }
                        ?.forEach {
                            it.delete()
                        }
                    logger.log(LogLevel.LIFECYCLE, "DecomposerPlugin: decomposed in ${output.path}")
                }
            }
    }
}
