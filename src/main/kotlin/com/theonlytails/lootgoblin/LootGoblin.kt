package com.theonlytails.lootgoblin

import com.theonlytails.lootgoblin.generation.DataGoblinEvent
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent
import kotlin.annotation.AnnotationTarget.*

/**
 * Creates a new [LootTable].
 *
 * @param parameterSet the [LootContextParamSet] type of the loot table.
 */
@LootGoblinDsl
fun DataGoblinEvent.lootTable(
	parameterSet: LootContextParamSet,
	body: LootTable.Builder.() -> LootTable.Builder,
) = lootTableBuilder(parameterSet, body).build().also {
	tables += it.lootTableId to it
} ?: throw LootTableCreationException("Something went wrong while creating a loot table")

/**
 * Creates a new [LootTable.Builder].
 *
 * @param parameterSet the [LootContextParamSet] type of the loot table.
 */
@Suppress("unused")
@LootGoblinDsl
fun DataGoblinEvent.lootTableBuilder(
	parameterSet: LootContextParamSet,
	body: LootTable.Builder.() -> LootTable.Builder,
) = LootTable.lootTable().body().setParamSet(parameterSet)
	?: throw LootTableCreationException("Something went wrong while creating a loot table")

/**
 * An annotation that marks every member of this DSL.
 */
@DslMarker
@MustBeDocumented
@Target(CLASS, FUNCTION, PROPERTY)
annotation class LootGoblinDsl

@LootGoblinDsl
class LootTableCreationException(message: String) : Exception(message)

@Mod("lootgoblin")
class LootGoblin {
	init {
		val modEventBus: IEventBus = FMLJavaModLoadingContext.get().modEventBus
		modEventBus.addListener<GatherDataEvent> {
			modEventBus.post(DataGoblinEvent(it.generator, it.includeServer(), it.includeClient()))
		}
	}
}