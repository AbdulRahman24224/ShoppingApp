import org.jetbrains.kotlin.config.JvmAnalysisFlags.useIR

plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KAPT)
    id(Plugins.HILT)
    id(Plugins.NAVIGATION)
}

android {

    namespace = "com.example.shoppingapp"
    compileSdk = Version.TARGET_SDK

    defaultConfig {
        applicationId = "com.example.shoppingapp"
        minSdk = Version.MIN_SDK
        targetSdk = Version.TARGET_SDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }



    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }


}

dependencies {

    implementation(Lib.COMPOSE_UI)
    implementation(Lib.COMPOSE_ACTIVITY)
    implementation(Lib.COMPOSE_MATERIAL)
    implementation(Lib.COMPOSE_ANIMATION)
    implementation(Lib.COMPOSE_UI_TOOLING)
    implementation(Lib.COMPOSE_LIFECYCLE_VIEWMODEL)

    implementation(Lib.KOTLIN_STDLIB)
    implementation(Lib.APPCOMPAT)
    implementation(Lib.MATERIAL)
    implementation(Lib.CONSTRAINTLAYOUT)

    //ktx
    implementation(Lib.CORE_KTX)
    implementation(Lib.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Lib.LIFECYCLE_RUNTIME_KTX)
    implementation(Lib.ACTIVITY_KTX)
    implementation(Lib.FRAGMENT_KTX)

    // network
    implementation(Lib.RETROFIT)
    implementation(Lib.RETROFIT_CONVERTER_GSON)
    implementation(Lib.GSON)
    implementation(Lib.OKHTTP3_LOGGING_INTERCEPTOR)

    implementation(Lib.TIMBER)

    implementation(Lib.ROOM_RUNTIME)
    implementation(Lib.ROOM_KTX)

    //DI
    implementation(Lib.HILT_ANDROID)
    implementation(Lib.HILT_WORK)
    kapt(Lib.HILT_WORK_KAPT)

    kapt(Lib.HILT_ANDROID_COMPILER)

    implementation(Lib.WORK)

    implementation(Lib.NAVIGATION_UI_KTX)
    implementation(Lib.NAVIGATION_FRAGMENT_KTX)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")

    implementation(project(Modules.PRESENTATION))
    implementation(project(Modules.BASE))
    implementation(project(Modules.RESOURCES))

    implementation(project(Modules.DOMAIN_MODELS))

    implementation(project(Modules.DATASOURCES))
    implementation(project(Modules.STORAGE))
    implementation(project(Modules.NETWORK))


    implementation(project(Modules.USECASES))
    implementation(project(Modules.USECASES_IMPL))

    implementation(project(Modules.RESOURCES))

    implementation(project(Modules.REPOSITORIES_IMPL))
    implementation(project(Modules.REPOSITORIES))
}