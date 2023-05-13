plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.NAVIGATION)
    id(Plugins.KAPT)
    id(Plugins.HILT)
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

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }


}

dependencies {

    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation("androidx.activity:activity-compose:1.3.1")

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

    implementation(Lib.SCANNER_DY)

    implementation(Lib.TIMBER)
    implementation(Lib.THREETENABP)


    implementation(Lib.ROOM_RUNTIME)
    implementation(Lib.ROOM_KTX)

    //DI
    implementation(Lib.HILT_ANDROID)
    implementation(Lib.HILT_WORK)
    kapt(Lib.HILT_WORK_KAPT)
    implementation(Lib.JSON)

    kapt(Lib.HILT_ANDROID_COMPILER)

    implementation(Lib.NAVIGATION_UI_KTX)
    implementation(Lib.NAVIGATION_FRAGMENT_KTX)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation(project(Modules.PRESENTATION))
    implementation(project(Modules.BASE))
    implementation(project(Modules.RESOURCES))

    implementation(project(Modules.DOMAIN_MODELS))

    implementation(project(Modules.DATASOURCES))
    implementation(project(Modules.STORAGE))
    implementation(project(Modules.NETWORK))
    implementation(project(Modules.BIOMETRIC))

//    implementation(project(Modules.NAVIGATION))
//    implementation(project(Modules.NAVIGATION_IMPL))

    implementation(project(Modules.USECASES))
    implementation(project(Modules.USECASES_IMPL))

    implementation(project(Modules.RESOURCES))

    implementation(project(Modules.REPOSITORIES_IMPL))
    implementation(project(Modules.REPOSITORIES))
}