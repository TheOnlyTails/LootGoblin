[![Maven metadata URL](https://img.shields.io/maven-metadata/v?color=blue&label=maven%20central&logo=gradle&metadataUrl=https%3A%2F%2Fs01.oss.sonatype.org%2Fservice%2Flocal%2Frepositories%2Freleases%2Fcontent%2Fcom%2Ftheonlytails%2lootgoblin%2Fmaven-metadata.xml&style=for-the-badge)](https://search.maven.org/artifact/com.theonlytails/lootgoblin)
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
    def lootGoblin = fg.deobf(project.dependencies.create(group: "com.theonlytails", name: "lootgoblin", version: VERSION) {
	    transitive = false
    })
    
	implementation fg.deobf(lootGoblin)
}
```

#### Gradle/Kotlin
```kotlin
repositories {
    mavenCentral()
}

dependencies {
    val lootGoblin = project.dependencies.create(group = "com.theonlytails", name = "lootgoblin", version = VERSION)
		.apply { isTransitive = false }

	implementation(fg.deobf(lootGoblin))
}
```

The `isTransitive` property is added to make sure the library is imported correctly.

---

Want to generate block models with a DSL like this? Check
out [ModelGoblin](https://github.com/theonlytails/ModelGoblin)!
