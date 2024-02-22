
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin-kapt")
}


android {
    namespace = "com.company.ait.tobemom"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.company.ait.tobemom"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }

        }

        dependencies {
            implementation("androidx.core:core-ktx:1.12.0")
            implementation("androidx.appcompat:appcompat:1.6.1")
            implementation("com.google.android.material:material:1.11.0")
            implementation("androidx.constraintlayout:constraintlayout:2.1.4")
            //implementation("androidx.design:design:1.1.0")
            //implementation("com.prolificinterative:material-calendarview:1.4.3")
            implementation("com.google.android.gms:play-services-maps:17.0.1")
            implementation("com.google.android.gms:play-services-location:18.0.1")
            implementation("com.google.android.libraries.places:places:3.3.0")
            implementation("noman.placesapi:placesAPI:1.1.3")
            implementation("com.google.maps.android:android-maps-utils:2.2.1")

            implementation("com.google.ai.client.generativeai:generativeai:0.2.0")
            testImplementation("junit:junit:4.13.2")
            androidTestImplementation("androidx.test.ext:junit:1.1.5")
            androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

            // 카카오 로그인
            implementation("com.kakao.sdk:v2-user:2.0.1")

            //Gson
            implementation("com.google.code.gson:gson:2.8.7")

            //RoomDB
            implementation("androidx.room:room-ktx:2.6.0")
            implementation("androidx.room:room-runtime:2.6.0")
            kapt("androidx.room:room-compiler:2.6.0")

            //Retrofit
            implementation("com.squareup.retrofit2:retrofit:2.9.0")
            implementation("com.squareup.retrofit2:converter-gson:2.9.0")
            implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

            //okHttp
            implementation("com.squareup.okhttp3:okhttp:4.9.0")
            implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
        }
    }
}