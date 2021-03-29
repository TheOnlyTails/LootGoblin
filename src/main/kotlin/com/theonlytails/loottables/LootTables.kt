package com.theonlytails.loottables

import net.minecraft.loot.*
import net.minecraft.loot.LootPool.lootPool
import net.minecraft.loot.LootTable.lootTable

/**
 * An annotation that marks every member of this DSL.
 *
 * @author TheOnlyTails
 */
@DslMarker
annotation class LootTablesDsl

/**
 * Creates a new [LootTable.Builder].
 *
 * @param parameterSet the [LootParameterSet] type of the loot table.
 * @param body a block of code that runs on the loot table and configures it.
 * @return the loot table builder.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun lootTableBuilder(parameterSet: LootParameterSet, body: LootTable.Builder.() -> LootTable.Builder): LootTable.Builder =
	lootTable().body().setParamSet(parameterSet)

/**
 * Creates a new [LootTable].
 *
 * @param parameterSet the [LootParameterSet] type of the loot table.
 * @param body a block of code that runs on the loot table and configures it.
 * @return the loot table builder.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun lootTable(parameterSet: LootParameterSet, body: LootTable.Builder.() -> LootTable.Builder): LootTable =
	lootTableBuilder(parameterSet, body).build()

/**
 * Adds a new [LootPool.Builder] to a [LootTable.Builder].
 *
 * @param rolls the number of rolls in this pool.
 * @param body a block of code that runs on the loot pool and configures it.
 * @return the loot table builder.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun LootTable.Builder.pool(rolls: Int = 1, body: LootPool.Builder.() -> Unit): LootTable.Builder =
	withPool(lootPool().setRolls(constantRange(rolls)).also(body))

/**
 * Adds a new [LootPool.Builder] to a [LootTable.Builder].
 *
 * @param rolls the range of rolls in this pool.
 * @param body a block of code that runs on the loot pool and configures it.
 * @return the loot table builder.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun LootTable.Builder.pool(rolls: IRandomRange, body: LootPool.Builder.() -> Unit): LootTable.Builder =
	withPool(lootPool().setRolls(rolls).also(body))