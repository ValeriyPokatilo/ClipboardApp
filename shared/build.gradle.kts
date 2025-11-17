import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("app.cash.sqldelight") version "2.1.0"
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true

            export(libs.mokoMvvm.core)
            export(libs.mokoMvvm.flow)
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.mokoMvvm.core)
            implementation(libs.mokoMvvm.flow)
            implementation(libs.kermit)
            implementation(libs.runtime)
            implementation(libs.coroutines.extensions)
        }
        androidMain.dependencies {
            api(libs.mokoMvvm.core)
            api(libs.mokoMvvm.flow)
            api(libs.mokoMvvm.flow.compose)
            api(libs.androidx.navigation.compose)
            implementation(libs.android.driver)
        }
        iosMain.dependencies {
            api(libs.mokoMvvm.core)
            api(libs.mokoMvvm.flow)
            implementation(libs.native.driver)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "app.xl.clipboardapp.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.example")
        }
    }
}
