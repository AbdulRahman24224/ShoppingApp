plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {

    namespace = "com.example.shoppingapp"
    compileSdk = Version.TARGET_SDK

    defaultConfig {
        minSdk = Version.MIN_SDK
        targetSdk = Version.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {



    implementation(project(Modules.BASE))
    implementation(project(Modules.RESOURCES))
    implementation(project(Modules.DOMAIN_MODELS))
    implementation(project(Modules.USECASES))
    implementation(project(Modules.BIOMETRIC))
}