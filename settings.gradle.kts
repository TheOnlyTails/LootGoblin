rootProject.name = "LootGoblin"

pluginManagement {
	repositories {
		maven(url = uri("https://maven.minecraftforge.net/"))
		mavenCentral()
		gradlePluginPortal()
	}
	resolutionStrategy {
		eachPlugin {
			if (requested.id.toString() == "net.minecraftforge.gradle")
				useModule("${requested.id}:ForgeGradle:${requested.version}")
		}
	}
	buildscript {
		repositories {
			mavenCentral()
		}
		dependencies {
			classpath(group = "com.vanniktech", name = "gradle-maven-publish-plugin", version = "latest.release")
		}
	}
}