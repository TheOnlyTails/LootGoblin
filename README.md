[![Maven metadata URL](https://img.shields.io/maven-central/v/com.theonlytails/lootgoblin?color=blue&style=for-the-badge)](https://search.maven.org/artifact/com.theonlytails/lootgoblin)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/TheOnlyTails/LootGoblin/Java%20CI%20with%20Gradle?label=gradle%20build&logo=github&style=for-the-badge)
![Kotlin](https://img.shields.io/badge/kotlin-%238052ff.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Gradle](https://img.shields.io/badge/gradle-%2302303A.svg?style=for-the-badge&logo=gradle&logoColor=white)
![GitHub License](https://img.shields.io/github/license/theonlytails/LootGoblin?style=for-the-badge&logo=key)

---

# ![LootGoblin](https://raw.githubusercontent.com/TheOnlyTails/DataGoblinAssets/main/LootGoblin/LootGoblin.svg#gh-light-mode-only)
# ![LootGoblin](https://raw.githubusercontent.com/TheOnlyTails/DataGoblinAssets/main/LootGoblin/LootGoblin_dark.svg#gh-dark-mode-only)

A Kotlin DSL for creating loot tables in Minecraft Forge mods.

For documentation and usage instructions, please take a look at
the [wiki](https://github.com/TheOnlyTails/LootGoblin/wiki) as well as the [docs](https://lootgoblin.theonlytails.com/).

Here's
the [`maven-metadata.xml`](https://s01.oss.sonatype.org/service/local/repositories/releases/content/com/theonlytails/lootgoblin/maven-metadata.xml)
of this library.

## Installation

_Don't forget to replace the VERSION key with the version in the top with the Maven Central badge at the top!_

#### Gradle/Groovy

```gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation fg.deobf("com.theonlytails:lootgoblin:VERSION")
}
```

#### Gradle/Kotlin
```kotlin
repositories {
    mavenCentral()
}

dependencies {
	implementation(fg.deobf(group = "com.theonlytails", name = "lootgoblin", version = "VERSION"))
}
```

---

Want to generate block models with a DSL like this? Check
out [ModelGoblin](https://github.com/theonlytails/ModelGoblin)!
