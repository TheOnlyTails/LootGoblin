package com.theonlytails.lootgoblin

import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootPool.lootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider
import kotlin.annotation.AnnotationTarget.*

/**
 * An annotation that marks every member of this DSL.
 *
 * @author TheOnlyTails
 */
@DslMarker
@MustBeDocumented
@Target(CLASS, FUNCTION, PROPERTY)
annotation class LootGoblin

@LootGoblin
class LootTableCreationException(message: String) : Exception(message)

/**
 * Creates a new [LootTable.Builder].
 *
 * @param parameterSet the [LootContextParamSet] type of the loot table.
 * @author TheOnlyTails
 */
@LootGoblin
fun lootTableBuilder(
	parameterSet: LootContextParamSet,
	body: LootTable.Builder.() -> LootTable.Builder,
) = LootTable.lootTable().body().setParamSet(parameterSet)
	?: throw LootTableCreationException("Something went wrong while creating a loot table")

/**
 * Creates a new [LootTable].
 *
 * @param parameterSet the [LootContextParamSet] type of the loot table.
 * @author TheOnlyTails
 */
@LootGoblin
fun lootTable(parameterSet: LootContextParamSet, body: LootTable.Builder.() -> LootTable.Builder) =
	lootTableBuilder(parameterSet, body).build()
		?: throw LootTableCreationException("Something went wrong while creating a loot table")

/**
 * Adds a new [LootPool.Builder] to a [LootTable.Builder].
 *
 * @param rolls the number of rolls in this pool.
 * @author TheOnlyTails
 */
@LootGoblin
fun LootTable.Builder.pool(rolls: Float = 1f, bonusRolls: Float = 0f, body: LootPool.Builder.() -> Unit) =
	withPool(lootPool()
		.setRolls(constantValue(rolls))
		.setBonusRolls(constantValue(bonusRolls))
		.also(body)
	) ?: throw LootTableCreationException("Something went wrong while adding a pool to a loot table")

/**
 * Adds a new [LootPool.Builder] to a [LootTable.Builder].
 *
 * @param rolls the number of rolls in this pool.
 * @author TheOnlyTails
 */
@LootGoblin
fun LootTable.Builder.pool(rolls: NumberProvider, bonusRolls: NumberProvider, body: LootPool.Builder.() -> Unit) =
	withPool(lootPool()
		.setRolls(rolls)
		.setBonusRolls(bonusRolls)
		.also(body)
	) ?: throw LootTableCreationException("Something went wrong while adding a pool to a loot table")
