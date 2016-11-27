group = "xyz.stavola.duration"
version = "1.0-SNAPSHOT"

buildscript {
    repositories {
        mavenCentral()
        gradleScriptKotlin()
    }
    dependencies {
        classpath(kotlinModule("gradle-plugin"))
    }
}

apply {
    plugin("kotlin")
}

repositories {
    mavenCentral()
}

val versions = mapOf(
        "junit" to "4.12"
)

dependencies {
    compile(kotlinModule("stdlib", "1.0.5"))

    testCompile(group="junit", name="junit", version=versions["junit"])
}