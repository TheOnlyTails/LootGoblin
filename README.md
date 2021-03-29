# LootTables
A Kotlin DSL for creating loot tables in Minecraft Forge mods.

## Installation


Gradle/Groovy:
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation "com.github.TheOnlyTails:LootTables:${version}"
}
```

Gradle/Kotlin:
```kotlin
repositories {
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(group = "com.github.TheOnlyTails", name = "LootTables", version = version)
}
```

Maven:
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
  
<dependency>
    <groupId>com.github.TheOnlyTails</groupId>
    <artifactId>LootTables</artifactId>
    <version>version</version>
</dependency>
```
