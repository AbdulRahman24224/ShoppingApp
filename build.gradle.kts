import org.jetbrains.kotlin.config.JvmAnalysisFlags.useIR

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Version.HILT}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Version.NAVIGATION}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects{

    afterEvaluate {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {

                if (configurations.findByName("kotlinCompilerPluginClasspath")
                        ?.dependencies
                        ?.any { it.group == "androidx.compose.compiler" } == true) {
                    freeCompilerArgs += listOf(
                        "-P", "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
                    )
                }
            }
        }
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}