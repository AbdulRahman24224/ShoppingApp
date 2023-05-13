// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {


    val compose_version by extra("1.3.0")
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Version.HILT}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Version.NAVIGATION}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}