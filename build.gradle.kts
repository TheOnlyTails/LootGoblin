
import com.vanniktech.maven.publish.MavenPublishPluginExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.time.Instant.now
import java.time.format.DateTimeFormatter.ISO_INSTANT

plugins {
	idea
	`java-library`
	kotlin("jvm") version "1.6.0"
	id("net.minecraftforge.gradle") version "5.+"
	id("com.vanniktech.maven.publish") version "latest.release"
	id("org.jetbrains.dokka") version "latest.release" // dokka
}

// Config -> Minecraft
val forgeVersion = findProperty("forge_version") as String
val minecraftVersion = findProperty("minecraft_version") as String
val projectVersion = findProperty("VERSION_NAME") as String
val projectGroup = findProperty("GROUP") as String
val modId = findProperty("POM_ARTIFACT_ID") as String
val projectName = findProperty("POM_NAME") as String
val projectAuthor = findProperty("POM_DEVELOPER_NAME") as String

// JVM Info
println("""
		Java: ${System.getProperty("java.version")} 
		JVM: ${System.getProperty("java.vm.version")} (${System.getProperty("java.vendor")}) 
		Arch: ${System.getProperty("os.arch")}
	""".trimIndent())

// Minecraft
minecraft {
	mappings("official", minecraftVersion)

	// @Suppress("SpellCheckingInspection")
	// accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

	runs {
		all {
			lazyToken("minecraft_classpath") {
				library.copyRecursive().resolve().joinToString(File.pathSeparator) { it.absolutePath }
			}
		}
	}
}

val library: Configuration by configurations.creating

configurations.implementation.get().extendsFrom(library)

repositories {
	mavenCentral()
}

dependencies {
	"minecraft"(group = "net.minecraftforge", name = "forge", version = "$minecraftVersion-$forgeVersion")
	library(group = "org.jetbrains.kotlin", name = "kotlin-stdlib", version = kotlin.coreLibrariesVersion) {
		exclude("org.jetbrains", "annotations")
	}
}

// Setup
project.group = projectGroup
project.version = projectVersion
base.archivesName.set(modId)

val javaVersion = 17

// Sets the toolchain to compile against Java 17
java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(javaVersion))
	}
}
kotlin {
	jvmToolchain {
		(this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(javaVersion))
	}
}

tasks.withType<KotlinCompile>().configureEach {
	kotlinOptions.jvmTarget = "$javaVersion"
}

// Finalize the jar by re-obfuscating
tasks.named<Jar>("jar") {
	// Manifest
	manifest {
		attributes(
			"Specification-Title" to projectName,
			"Specification-Vendor" to projectAuthor,
			"Specification-Version" to "1",
			"Implementation-Title" to projectName,
			"Implementation-Version" to project.version,
			"Implementation-Vendor" to projectName,
			"Implementation-Timestamp" to ISO_INSTANT.format(now()),
			"FMLModType" to "GAMELIBRARY",
		)
	}

	finalizedBy("reobfJar")
}

// Dokka
tasks.dokkaHtml.configure {
	outputDirectory.set(projectDir.resolve("docs"))
}

// Publishing to maven central
extensions.getByType<MavenPublishPluginExtension>().sonatypeHost = SonatypeHost.S01
