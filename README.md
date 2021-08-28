# decomposer
Gradle Plugin that allows you to decompile bytecode compiled with Jetpack Compose Compiler Plugin into Java and check it

# How to use


## Run build

```
> Task :uicomponent-compose:compileDebugKotlin
DecomposerPlugin: decomposed in /Users/takahirom/git/.../uicomponent-compose/build/decompile
```

## Now, you can check the Java code in `build/decompile`

```java
   @Composable
   private static final void AudioControlButton(final Modifier modifier, final boolean isVisible, final boolean isPlayingPodcast, Composer $composer, final int $changed) {
...
      if (($dirty & 731 ^ 146) == 0 && $composer.getSkipping()) {
         $composer.skipToGroupEnd();
      } else if (isVisible) {
         $composer.startReplaceableGroup(445067107);
         ComposerKt.sourceInformation($composer, "");
         Painter var6;
         if (isPlayingPodcast) {
            $composer.startReplaceableGroup(445067139);
...
```


## Download

If you are using pluginManagement in settings.gradle.

settings.gradle

```kotlin
pluginManagement {
    repositories {
        gradlePluginPortal()
        ....
        maven { url 'https://jitpack.io' }
    }
    ...
    resolutionStrategy {
        eachPlugin {
            if(requested.id.toString() == "com.github.takahirom.decomposer"){
                useModule("com.github.takahirom:decomposer:main-SNAPSHOT")
            }
        }
    }
```


```kotlin
plugins {
    id 'com.android.library'
    id 'kotlin-android'
    ...
    id "com.github.takahirom.decomposer"
}
```

If you use buildscript (not checked) 

```kotlin
buildscript {
    repositories {
        mavenCentral()
        maven {
            url 'https://jitpack.io'
        }
    }
    dependencies {
        classpath 'com.github.takahirom:decomposer:main-SNAPSHOT'
    }
}
apply plugin: 'com.github.takahirom.decomposer'
```


## Credit
This plugin uses Fernflower
https://github.com/JetBrains/intellij-community/tree/4becae3818835d6105dbcf6c55ece50846395290/plugins/java-decompiler/engine
