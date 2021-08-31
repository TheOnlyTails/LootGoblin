rootProject.name = "LootGoblin"

pluginManagement {
	repositories {
		maven(url = uri("https://maven.minecraftforge.net/"))
		mavenCentral()
		gradlePluginPortal()
	}
	plugins {
		id("net.minecraftforge.gradle")
	}
	resolutionStrategy {
		eachPlugin {
			if (requested.id.id == "net.minecraftforge.gradle") useModule("net.minecraftforge.gradle:ForgeGradle:${extra["forgeGradleVersion"]}")
			else if (requested.id.id == "com.vanniktech.maven.publish") useModule("com.vanniktech:gradle-maven-publish-plugin:latest.release")
		}
	}
}