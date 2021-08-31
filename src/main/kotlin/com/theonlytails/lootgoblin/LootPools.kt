package com.theonlytails.lootgoblin

import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer
import net.minecraft.world.level.storage.loot.functions.LootItemFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraft.world.level.storage.loot.LootPool.Builder as Pool

typealias LootEntryBuilder = LootPoolEntryContainer.Builder<*>
typealias LootFunctionBuilder = LootItemFunction.Builder
typealias LootConditionBuilder = LootItemCondition.Builder

/**
 * Adds a [LootPoolEntryContainer] to a [Pool].
 *
 * @author TheOnlyTails
 */
@LootGoblin
fun LootEntryBuilder.add(pool: Pool) = pool.add(this)
	?: throw LootTableCreationException("Something went wrong while adding a loot entry to a pool")

/**
 * Adds multiple [LootPoolEntryContainer] to a [Pool].
 *
 *  @author TheOnlyTails
 */
@LootGoblin
fun Pool.add(vararg entries: LootEntryBuilder) = entries.forEach { add(it) }

/**
 * Adds a condition to a [Pool].
 *
 * @author TheOnlyTails
 */
@LootGoblin
fun Pool.condition(getCondition: () -> LootConditionBuilder) = `when`(getCondition())
	?: throw LootTableCreationException("Something went wrong while adding a condition to a loot pool")

/**
 * Adds a function to a [Pool].
 *
 * @author TheOnlyTails
 */
@LootGoblin
fun Pool.function(getFunction: () -> LootFunctionBuilder) = apply(getFunction())
	?: throw LootTableCreationException("Something went wrong while adding a function to a loot pool")

/**
 * Adds a list of conditions to a [Pool].
 *
 * @author TheOnlyTails
 */
@LootGoblin
fun Pool.condition(vararg conditions: LootConditionBuilder) = this.also {
	conditions.forEach { condition { it } }
}

/**
 * Adds a list of functions to a [Pool].
 *
 * @author TheOnlyTails
 */
@LootGoblin
fun Pool.function(vararg functions: LootFunctionBuilder) = this.also {
	functions.forEach { function { it } }
}