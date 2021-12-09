package com.theonlytails.lootgoblin

import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet
import kotlin.annotation.AnnotationTarget.*

/**
 * Creates a new [LootTable].
 *
 * @param parameterSet the [LootContextParamSet] type of the loot table.
 */
@LootGoblinDsl
fun lootTable(
	parameterSet: LootContextParamSet,
	body: LootTable.Builder.() -> LootTable.Builder,
) = lootTableBuilder(parameterSet, body).build()
	?: throw LootTableCreationException("Something went wrong while creating a loot table")

/**
 * Creates a new [LootTable.Builder].
 *
 * @param parameterSet the [LootContextParamSet] type of the loot table.
 */
@LootGoblinDsl
fun lootTableBuilder(
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
internal annotation class LootGoblinDsl

@LootGoblinDsl
class LootTableCreationException(message: String) : Exception(message)