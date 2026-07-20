plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.17.4"
}

group = "letcode.plugin"
version = "1.1.0"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

intellij {
    version.set("2023.3.8")
    type.set("IC")
    plugins.set(listOf("com.intellij.java"))
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    patchPluginXml {
        sinceBuild.set("233")
    }
    buildSearchableOptions {
        enabled = false
    }
}
