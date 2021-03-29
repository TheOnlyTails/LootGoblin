package com.theonlytails.loottables

import net.minecraft.item.Item
import net.minecraft.loot.*
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
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.itemEntry(
	item: IItemProvider,
): StandaloneEntry<*> = ItemLootEntry.lootTableItem(item).also { add(it) }

/**
 * Adds an [ItemLootEntry] to a [Pool].
 *
 * @param item the item of the entry.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.itemEntry(
	item: IItemProvider,
	body: StandaloneEntry<*>.() -> Entry<*>,
) = itemEntry(item).body().also { add(it) }

/**
 * Adds a [TagLootEntry] to a [Pool].
 *
 * @param tag the [ITag] of the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.tagEntry(
	tag: ITag<Item>,
): StandaloneEntry<*> = TagLootEntry.expandTag(tag).also { add(it) }

/**
 * Adds a [TagLootEntry] to a [Pool].
 *
 * @param tag the [ITag] of the entry.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.tagEntry(
	tag: ITag<Item>,
	body: StandaloneEntry<*>.() -> Entry<*>,
) = tagEntry(tag).body().also { add(it) }

/**
 * Adds a [TableLootEntry] to a [Pool].
 *
 * @param lootTable the loot table being referenced in the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.tableEntry(
	lootTable: ResourceLocation,
): StandaloneEntry<*> = TableLootEntry.lootTableReference(lootTable).also { add(it) }

/**
 * Adds a [TableLootEntry] to a [Pool].
 *
 * @param lootTable the loot table being referenced in the entry.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.tableEntry(
	lootTable: ResourceLocation,
	body: StandaloneEntry<*>.() -> Entry<*>,
) = tableEntry(lootTable).body().also { add(it) }

/**
 * Adds a [DynamicLootEntry] to a [Pool].
 *
 * @param id the id of the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.dynamicEntry(
	id: ResourceLocation,
): StandaloneEntry<*> = DynamicLootEntry.dynamicEntry(id).also { add(it) }

/**
 * Adds a [DynamicLootEntry] to a [Pool].
 *
 * @param id the id of the entry.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.dynamicEntry(
	id: ResourceLocation,
	body: StandaloneEntry<*>.() -> Entry<*>,
) = dynamicEntry(id).body().also { add(it) }

/**
 * Adds an [AlternativesLootEntry] to a [Pool].
 *
 * @param entries the sub-entries of the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.alternativesEntry(
	vararg entries: Entry<*>,
): AlternativesLootEntry.Builder = AlternativesLootEntry.alternatives(*entries).also { add(it) }

/**
 * Adds an [AlternativesLootEntry] to a [Pool].
 *
 * @param entries the sub-entries of the entry.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.alternativesEntry(
	vararg entries: Entry<*>,
	body: AlternativesLootEntry.Builder.() -> AlternativesLootEntry.Builder,
) = alternativesEntry(*entries).body().also { add(it) }

/**
 * Adds an [EmptyLootEntry] to a [Pool].
 *
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.emptyEntry(): StandaloneEntry<*> = EmptyLootEntry.emptyItem().also { add(it) }

/**
 * Adds an [EmptyLootEntry] to a [Pool].
 *
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.emptyEntry(body: StandaloneEntry<*>.() -> StandaloneEntry<*>) =
	emptyEntry().body().also { add(it) }

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