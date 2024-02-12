plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.alacrity.agora_gallery.app"
    compileSdk = Dependencies.android.compileSdk
    buildToolsVersion = Dependencies.android.buildTools

    defaultConfig {
        applicationId = "com.alacrity.agora_gallery"
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.unsplash.com/\"")
            buildConfigField(
                "String",
                "ACCESS_KEY",
                "\"tXv8eoxALDAMicepbiLE0swZ8E_-J0v-jpzu0qCUo1A\""
            )
        }
        release {
            buildConfigField(
                "String",
                "ACCESS_KEY",
                "\"tXv8eoxALDAMicepbiLE0swZ8E_-J0v-jpzu0qCUo1A\""
            )
            buildConfigField("String", "BASE_URL", "\"https://unsplash.com\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExt
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    core()
    di()
    log()
    room()
    async()
    jetpack()
    retrofit()
    imageLoading()
}

fun DependencyHandlerScope.core() {
    implementation(Dependencies.other.kotlin)
    implementation(Dependencies.other.ktxCore)
    implementation(Dependencies.other.appcompat)
    implementation(Dependencies.other.material)
}

fun DependencyHandlerScope.room() {
    implementation(Dependencies.room.runtime)
    kapt(Dependencies.room.compiler)
    implementation(Dependencies.room.ktx)
}

fun DependencyHandlerScope.di() {
    implementation(Dependencies.di.dagger)
    kapt(Dependencies.di.daggerCompiler)
}

fun DependencyHandlerScope.imageLoading() {
    implementation(Dependencies.image.glide)
}

fun DependencyHandlerScope.async() {
    implementation(Dependencies.async.coroutinesCore)
    implementation(Dependencies.async.coroutinesAndroid)
}

fun DependencyHandlerScope.log() {
    implementation(Dependencies.other.timber)
}

fun DependencyHandlerScope.jetpack() {
    implementation(Dependencies.jetpack.lifecycleExtensions)
    implementation(Dependencies.jetpack.lifecycleViewModel)
    implementation(Dependencies.compose.runtime)
    implementation(Dependencies.compose.ui)
    implementation(Dependencies.compose.foundationLayout)
    implementation(Dependencies.compose.material)
    implementation(Dependencies.compose.icons)
    implementation(Dependencies.compose.foundation)
    implementation(Dependencies.compose.animation)
    implementation(Dependencies.compose.activity)
    implementation(Dependencies.compose.navigation)
    implementation(Dependencies.compose.uiController)
    implementation(Dependencies.compose.lifecycleRuntime)
}

fun DependencyHandlerScope.retrofit() {
    implementation(Dependencies.retrofit.retrofit)
    implementation(Dependencies.retrofit.gson)
    implementation(Dependencies.retrofit.gsonConverter)
    implementation(Dependencies.other.moshi)
    implementation(Dependencies.other.moshiConverter)
    implementation(Dependencies.other.moshiKotlin)
    kapt(Dependencies.other.moshiCodGen)
}

