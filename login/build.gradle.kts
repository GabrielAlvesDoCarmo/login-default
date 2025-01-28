plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("maven-publish")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "br.com.gds.login"
    compileSdk = 34

    defaultConfig {
        minSdk = 28
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        multiDexEnabled = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/AL2.0"
            excludes += "META-INF/LGPL2.1"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
            excludes += "META-INF/NOTICE"
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>(name = "release") {
                from(components["release"])
                groupId = "com.github.GabrielAlvesDoCarmo"
                artifactId = "login-default"
                version = "0.0.13"
            }
        }
    }
}

dependencies {
    implementation(libs.default.core)
    implementation(libs.default.design.system)


    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.koin.android)
    implementation(libs.material)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.biometric)

    implementation("androidx.datastore:datastore-preferences:1.1.2")
    implementation("androidx.datastore:datastore:1.1.2")

    testImplementation(libs.junit)
    testImplementation(libs.koin.test.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.androidx.fragment.testing)
    implementation("org.mockito.kotlin:mockito-kotlin:5.4.0")
    implementation("io.mockk:mockk:1.13.13")
    implementation("io.mockk:mockk-android:1.13.13")

    implementation("com.github.santalu:mask-edittext:1.1.1")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.test:runner:1.6.2")
    androidTestImplementation("androidx.test:rules:1.6.1")

    // Koin Test
    androidTestImplementation("io.insert-koin:koin-test:4.0.0") // ou a versão mais recente
    androidTestImplementation("io.insert-koin:koin-test-junit4:4.0.0") // para usar com JUnit4

    androidTestImplementation( "androidx.arch.core:core-testing:2.2.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")



}

tasks.register("generateReleaseTag") {
    doLast {
        val versionName = project.properties["versionName"] as String
        val tagName = "v${versionName}"
        exec {
            commandLine("git", "tag", "-a", tagName, "-m", "Release $versionName")
        }
    }
}

tasks.register("generateReleaseVersion") {
    doLast {
        val versionName = project.properties["versionName"] as String
        val nextVersionName = incrementVersionName(versionName)

        // Atualiza o versionName no arquivo build.gradle.kts
        val buildFile = project.file("build.gradle.kts")
        buildFile.writeText(
            buildFile.readText().replace(Regex("versionName = \"$versionName\""), "versionName = \"$nextVersionName\"")
        )

        // Adiciona e commita as mudanças
        exec {
            commandLine("git", "add", "build.gradle.kts")
        }
        exec {
            commandLine("git", "commit", "-m", "Bump version to $nextVersionName")
        }
    }
}

fun incrementVersionName(versionName: String, incrementPosition: Int = 3): String {
    val parts = versionName.split(".")
    if (incrementPosition < 0 || incrementPosition >= parts.size) {
        throw IllegalArgumentException("Invalid increment position: $incrementPosition")
    }

    val incrementedPart = parts[incrementPosition].toInt() + 1
    val newParts = parts.toMutableList()
    newParts[incrementPosition] = incrementedPart.toString()

    // Resetar os dígitos à direita do dígito incrementado para 0
    for (i in incrementPosition + 1 until newParts.size) {
        newParts[i] = "0"
    }

    return newParts.joinToString(".")
}

kapt {
    correctErrorTypes = true
    generateStubs = true
}