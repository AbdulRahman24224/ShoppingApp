plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_PARCELIZE)
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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE
    }

}

dependencies {

    implementation(Lib.COMPOSE_UI)
    implementation(Lib.COMPOSE_MATERIAL)
    implementation(Lib.COMPOSE_ANIMATION)
    implementation(Lib.COMPOSE_ACTIVITY)
    implementation(Lib.COMPOSE_UI_TOOLING)
    implementation(Lib.COMPOSE_LIFECYCLE_VIEWMODEL)
    implementation(Lib.CONSTRAINTLAYOUT_COMPOSE)
    implementation(Lib.ACCOMPANIST_PLACEHOLDER)

    implementation(Lib.KOTLIN_STDLIB)
    implementation(Lib.ANDROIDX_ANNOTATION)
    implementation(Lib.CORE_KTX)
    implementation(Lib.APPCOMPAT)
    implementation(Lib.FRAGMENT_KTX)
    implementation(Lib.KOTLINX_COROUTINES_ANDROID)
    implementation(Lib.TIMBER)
    implementation(Lib.VIEWBINDING_PROPERTY_DELEGATE_NO_REFLECTION)
    implementation(Lib.NAVIGATION_FRAGMENT_KTX)

    implementation(Lib.DATASTORE)
    implementation(Lib.PROTOBUF_JAVALITE)

    implementation(project(Modules.DOMAIN_MODELS))
}