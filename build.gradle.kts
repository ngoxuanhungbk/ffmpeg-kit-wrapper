plugins {
    id("com.android.library") version "8.9.0"
    id("org.jetbrains.kotlin.android") version "2.0.21" // Nếu bạn dùng Kotlin
    id("maven-publish") // Áp dụng plugin publish
}

android {
    namespace = "com.leansoft.ffmpeg.wrapper"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

afterEvaluate {
    extensions.configure<PublishingExtension>("publishing") {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = "com.github.ngoxuanhungbk"
                artifactId = "ffmpeg-kit-wrapper"
                version = "6.0.4"
            }
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
}