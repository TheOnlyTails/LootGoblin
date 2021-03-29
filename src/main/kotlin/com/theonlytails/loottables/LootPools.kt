package com.theonlytails.loottables

import net.minecraft.loot.LootEntry
import net.minecraft.loot.conditions.ILootCondition
import net.minecraft.loot.functions.ILootFunction
import net.minecraft.loot.LootPool.Builder as Pool

/**
 * Adds a [LootEntry] to a [Pool].
 *
 * @receiver a [LootEntry].
 * @param pool the pool the entry gets added to.
 * @return the pool, with the entry added.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun LootEntry.Builder<*>.add(pool: Pool): Pool = pool.add(this)

/**
 * Adds multiple [LootEntry] to a [Pool].
 *
 * @receiver a [Pool].
 * @param entries the list of entries being added to the pool.
 * @return the pool, with the entries added.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.add(vararg entries: LootEntry.Builder<*>) = entries.forEach { add(it) }

/**
 * Adds a condition to a [Pool].
 *
 * @receiver a [Pool].
 * @param getCondition a lambda that returns the condition.
 * @return the original pool, with the condition added.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.condition(getCondition: () -> ILootCondition.IBuilder): Pool = `when`(getCondition())

/**
 * Adds a function to a [Pool].
 *
 * @receiver a [Pool].
 * @param getFunction a lambda that returns the function.
 * @return the original pool, with the function added.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.function(getFunction: () -> ILootFunction.IBuilder): Pool = apply(getFunction())