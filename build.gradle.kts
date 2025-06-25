plugins {
    id("com.android.library") version "7.4.2"
    id("maven-publish") // Áp dụng plugin publish
}

repositories {
    google()
    mavenCentral()
    flatDir {
        dirs("libs")
    }
}

android {
    namespace = "com.leansoft.ffmpeg.wrapper"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        dataBinding = false
        viewBinding = false
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
    publishing {
        singleVariant("release")
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
    api(name = "ffmpeg-kit-full-gpl-6.0-2", ext = "aar")
}

configurations {
    api {
        isCanBeResolved = true
    }
}