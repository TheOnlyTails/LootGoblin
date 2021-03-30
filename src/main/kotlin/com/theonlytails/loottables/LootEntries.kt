package com.theonlytails.loottables

import net.minecraft.item.Item
import net.minecraft.loot.*
import net.minecraft.loot.EmptyLootEntry.emptyItem
import net.minecraft.loot.ItemLootEntry.lootTableItem
import net.minecraft.loot.conditions.ILootCondition
import net.minecraft.loot.functions.ILootFunction
import net.minecraft.tags.ITag
import net.minecraft.util.IItemProvider
import net.minecraft.util.ResourceLocation
import net.minecraft.loot.LootEntry.Builder as Entry
import net.minecraft.loot.LootPool.Builder as Pool
import net.minecraft.loot.StandaloneLootEntry.Builder as StandaloneEntry

/**
 * Adds an [ItemLootEntry] to a [Pool].
 *
 * @param item the item of the entry.
 * @param addToPool controls whether this entry should be added to the pool.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.itemEntry(
	item: IItemProvider,
	addToPool: Boolean = true,
	body: StandaloneEntry<*>.() -> StandaloneEntry<*> = { this },
) = lootTableItem(item).body().also { if (addToPool) add(it) }

/**
 * Adds a [TagLootEntry] to a [Pool].
 *
 * @param tag the [ITag] of the entry.
 * @param addToPool controls whether this entry should be added to the pool.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.tagEntry(
	tag: ITag<Item>,
	addToPool: Boolean = true,
	body: StandaloneEntry<*>.() -> StandaloneEntry<*> = { this },
) = TagLootEntry.expandTag(tag).body().also { if (addToPool) add(it) }

/**
 * Adds a [TableLootEntry] to a [Pool].
 *
 * @param lootTable the loot table being referenced in the entry.
 * @param addToPool controls whether this entry should be added to the pool.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.tableEntry(
	lootTable: ResourceLocation,
	addToPool: Boolean = true,
	body: StandaloneEntry<*>.() -> StandaloneEntry<*> = { this },
) = TableLootEntry.lootTableReference(lootTable).body().also { if (addToPool) add(it) }

/**
 * Adds a [DynamicLootEntry] to a [Pool].
 *
 * @param id the id of the entry.
 * @param addToPool controls whether this entry should be added to the pool.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.dynamicEntry(
	id: ResourceLocation,
	addToPool: Boolean = true,
	body: StandaloneEntry<*>.() -> StandaloneEntry<*> = { this },
) = dynamicLootEntry(id).body().also { if (addToPool) add(it) }

/**
 * Adds an [AlternativesLootEntry] to a [Pool].
 *
 * @param entries the sub-entries of the entry.
 * @param addToPool controls whether this entry should be added to the pool.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.alternativesEntry(
	vararg entries: Entry<*>,
	addToPool: Boolean = true,
	body: AlternativesLootEntry.Builder.() -> AlternativesLootEntry.Builder = { this },
) = AlternativesLootEntry.alternatives(*entries).body().also { if (addToPool) add(it) }

/**
 * Adds an [EmptyLootEntry] to a [Pool].
 *
 * @param addToPool controls whether this entry should be added to the pool.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.emptyEntry(
	addToPool: Boolean = true,
	body: StandaloneEntry<*>.() -> StandaloneEntry<*> = { this },
) = emptyItem().body().also { if (addToPool) add(it) }

/**
 * Adds a condition to an [Entry].
 *
 * @receiver an [Entry].
 * @param getCondition a lambda that returns the condition.
 * @return the original entry, with the condition added.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Entry<*>.condition(getCondition: () -> ILootCondition.IBuilder): Entry<*> =
	`when`(getCondition())

/**
 * Adds a function to a [StandaloneEntry].
 *
 * @receiver a [StandaloneEntry].
 * @param getFunction a lambda that returns the function.
 * @return the original entry, with the function added.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun StandaloneEntry<*>.function(getFunction: () -> ILootFunction.IBuilder): StandaloneEntry<*> =
	apply(getFunction())

/**
 * This solves some recursion issues in [dynamicEntry].
 *
 * @param id the id of the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
private fun dynamicLootEntry(id: ResourceLocation) = DynamicLootEntry.dynamicEntry(id)