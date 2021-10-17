package com.theonlytails.lootgoblin.generation

import com.theonlytails.lootgoblin.LootGoblinDsl
import net.minecraft.data.DataGenerator
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraftforge.eventbus.api.Event
import net.minecraftforge.fml.event.IModBusEvent

@LootGoblinDsl
class DataGoblinEvent(
	@LootGoblinDsl val generator: DataGenerator,
	@LootGoblinDsl val isServer: Boolean = true,
	@LootGoblinDsl val isClient: Boolean = true,
) : Event(), IModBusEvent {
	/**
	 * A list of all loot tables added for generation.
	 */
	@LootGoblinDsl
	val tables = mutableMapOf<ResourceLocation, LootTable>()

	/**
	 * A toggle to disable data generation via the DSL.
	 */
	@LootGoblinDsl
	var generateTables = true

	init {
		if (generateTables) {
			if (isServer) generator.addProvider(LootGoblinGenerator(generator, tables))
		}
	}

	/**
	 * Builds a [LootTable.Builder] and generates it using the built-in generator.
	 */
	@Suppress("unused")
	@LootGoblinDsl
	fun LootTable.Builder.generate() {
		val lootTable = this.build()
		tables += lootTable.lootTableId to lootTable
	}

	/**
	 * Runs the generation logic passed in.
	 */
	@LootGoblinDsl
	inline operator fun invoke(body: DataGoblinEvent.() -> Unit) = body()
}