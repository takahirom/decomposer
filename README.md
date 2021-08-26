# decomposer
Gradle Plugin that allows you to decompile bytecode compiled with Jetpack Compose Compiler Plugin into Java and check it

# How to use

## Apply plugin

```kotlin
plugins {
    id 'com.android.library'
    id 'kotlin-android'
    ...
    id "com.github.takahirom.decomposer"
}
```

## Run build

```
> Task :uicomponent-compose:compileDebugKotlin
DecomposerPlugin: decomposed in /Users/takahirom/git/.../uicomponent-compose/build/decompile
```

## Now, you can check the Java code

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


## Credit
This plugin uses Fernflower
https://github.com/JetBrains/intellij-community/tree/4becae3818835d6105dbcf6c55ece50846395290/plugins/java-decompiler/engine
