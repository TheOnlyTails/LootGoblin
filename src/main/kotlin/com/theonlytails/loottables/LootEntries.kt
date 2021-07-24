package com.theonlytails.loottables

import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.Tag
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.storage.loot.entries.*
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem.emptyItem
import net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem
import net.minecraft.world.level.storage.loot.entries.LootTableReference.lootTableReference
import net.minecraft.world.level.storage.loot.LootPool.Builder as Pool
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem as EmptyEntry
import net.minecraft.world.level.storage.loot.entries.LootItem as ItemEntry
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer as Entry
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer.Builder as StandaloneEntry

typealias EntryBuilder = Entry.Builder<*>

/**
 * Adds an [ItemEntry] to a [Pool].
 *
 * @param item the item of the entry.
 * @param addToPool controls whether this entry should be added to the pool.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTables
fun Pool.itemEntry(
	item: ItemLike,
	weight: Int = 1,
	quality: Int = 0,
	addToPool: Boolean = true,
	body: StandaloneEntry<*>.() -> Unit = { },
) = lootTableItem(item).also { it.setWeight(weight).setQuality(quality).body(); if (addToPool) this.add(it) }
	?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: item")

/**
 * Adds an [ItemEntry] to a [AlternativesEntry.Builder].
 *
 * @param item the item of the entry.
 * @param addToEntry controls whether this entry should be added to the over-arching entry.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTables
fun AlternativesEntry.Builder.itemEntry(
	item: ItemLike,
	weight: Int = 1,
	quality: Int = 0,
	addToEntry: Boolean = true,
	body: StandaloneEntry<*>.() -> Unit = { },
) = lootTableItem(item).also { it.setWeight(weight).setQuality(quality).body(); if (addToEntry) this.otherwise(it) }
	?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: item")

/**
 * Adds a [TagEntry] to a [Pool].
 *
 * @param tag the [Tag] of the entry.
 * @param addToPool controls whether this entry should be added to the pool.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTables
fun Pool.tagEntry(
	tag: Tag<Item>,
	weight: Int = 1,
	quality: Int = 0,
	addToPool: Boolean = true,
	body: StandaloneEntry<*>.() -> Unit = { },
) = TagEntry.expandTag(tag).also { it.setWeight(weight).setQuality(quality).body(); if (addToPool) this.add(it) }
	?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: tag")

/**
 * Adds a [TagEntry] to a [AlternativesEntry.Builder].
 *
 * @param tag the [Tag] of the entry.
 * @param addToEntry controls whether this entry should be added to the over-arching entry.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTables
fun AlternativesEntry.Builder.tagEntry(
	tag: Tag<Item>,
	weight: Int = 1,
	quality: Int = 0,
	addToEntry: Boolean = true,
	body: StandaloneEntry<*>.() -> Unit = { },
) = TagEntry.expandTag(tag)
	.also { it.setWeight(weight).setQuality(quality).body(); if (addToEntry) this.otherwise(it) }
	?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: tag")

/**
 * Adds a [LootTableReference] to a [Pool].
 *
 * @param lootTable the loot table being referenced in the entry.
 * @param addToPool controls whether this entry should be added to the pool.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTables
fun Pool.tableEntry(
	lootTable: ResourceLocation,
	weight: Int = 1,
	quality: Int = 0,
	addToPool: Boolean = true,
	body: StandaloneEntry<*>.() -> Unit = { },
) = lootTableReference(lootTable)
	.also { it.setWeight(weight).setQuality(quality).body(); if (addToPool) this.add(it) }
	?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: loot_table")

/**
 * Adds a [LootTableReference] to a [AlternativesEntry.Builder].
 *
 * @param lootTable the loot table being referenced in this entry.
 * @param addToEntry controls whether this entry should be added to the over-arching.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTables
fun AlternativesEntry.Builder.tableEntry(
	lootTable: ResourceLocation,
	weight: Int = 1,
	quality: Int = 0,
	addToEntry: Boolean = true,
	body: StandaloneEntry<*>.() -> Unit = { },
) = lootTableReference(lootTable)
	.also { it.setWeight(weight).setQuality(quality).body(); if (addToEntry) this.otherwise(it) }
	?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: loot_table")

/**
 * Adds a [DynamicLoot] to a [Pool].
 *
 * @param id the id of the entry.
 * @param addToPool controls whether this entry should be added to the pool.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTables
fun Pool.dynamicEntry(
	id: ResourceLocation,
	weight: Int = 1,
	quality: Int = 0,
	addToPool: Boolean = true,
	body: StandaloneEntry<*>.() -> Unit = { },
) = DynamicLoot.dynamicEntry(id).also { it.setWeight(weight).setQuality(quality).body(); if (addToPool) this.add(it) }
	?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: dynamic")

/**
 * Adds a [DynamicLoot] to a [AlternativesEntry.Builder].
 *
 * @param id the id of the entry.
 * @param addToEntry controls whether this entry should be added to the over-arching entry.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTables
fun AlternativesEntry.Builder.dynamicEntry(
	id: ResourceLocation,
	weight: Int = 1,
	quality: Int = 0,
	addToEntry: Boolean = true,
	body: StandaloneEntry<*>.() -> Unit = { },
) = dynamicLootEntry(id).also { it.setWeight(weight).setQuality(quality).body(); if (addToEntry) this.otherwise(it) }
	?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: dynamic")

/**
 * Adds an [AlternativesEntry] to a [Pool].
 *
 * @param entries the sub-entries of the entry.
 * @param addToPool controls whether this entry should be added to the pool.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTables
fun Pool.alternativesEntry(
	vararg entries: EntryBuilder,
	addToPool: Boolean = true,
	body: AlternativesEntry.Builder.() -> Unit = { },
) = AlternativesEntry.alternatives(*entries).also { it.body(); if (addToPool) this.add(it) }
	?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: alternatives")

/**
 * Adds an [AlternativesEntry] to a [AlternativesEntry.Builder].
 *
 * @param entries the sub-entries of the entry.
 * @param addToPool controls whether this entry should be added to the over-arching entry.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTables
fun AlternativesEntry.Builder.alternativesEntry(
	vararg entries: EntryBuilder,
	addToPool: Boolean = true,
	body: AlternativesEntry.Builder.() -> Unit = { },
) = AlternativesEntry.alternatives(*entries).also { it.body(); if (addToPool) this.otherwise(it) }
	?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: alternatives")

/**
 * Adds an [EmptyEntry] to a [Pool].
 *
 * @param addToPool controls whether this entry should be added to the pool.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTables
fun Pool.emptyEntry(
	weight: Int = 1,
	quality: Int = 0,
	addToPool: Boolean = true,
	body: StandaloneEntry<*>.() -> Unit = { },
) = emptyItem()
	.also { it.setWeight(weight).setQuality(quality).body(); if (addToPool) this.add(it) }
	?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: empty")

/**
 * Adds an [EmptyEntry] to an [AlternativesEntry].
 *
 * @param addToEntry controls whether this entry should be added to the overarching entry.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTables
fun AlternativesEntry.Builder.emptyEntry(
	weight: Int = 1,
	quality: Int = 0,
	addToEntry: Boolean = true,
	body: StandaloneEntry<*>.() -> Unit = { },
) = emptyItem().also { it.setWeight(weight).setQuality(quality).body(); if (addToEntry) this.otherwise(it) }
	?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: empty")

/**
 * Adds a condition to an [Entry].
 *
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTables
fun EntryBuilder.condition(getCondition: () -> LootConditionBuilder) = `when`(getCondition())
	?: throw LootTableCreationException("Something went wrong while adding a condition to a loot entry")

/**
 * Adds a function to a [StandaloneEntry].
 *
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTables
fun StandaloneEntry<*>.function(getFunction: () -> LootFunctionBuilder) = apply(getFunction())
	?: throw LootTableCreationException("Something went wrong while adding a function to a loot entry")

/**
 * Adds a list of conditions to a [Pool].
 *
 * @author TheOnlyTails
 */
@LootTables
fun EntryBuilder.condition(vararg conditions: LootConditionBuilder) = this.also {
	conditions.forEach { condition { it } }
}

/**
 * Adds a list of functions to a [Pool].
 *
 * @author TheOnlyTails
 */
@LootTables
fun StandaloneEntry<*>.function(vararg functions: LootFunctionBuilder) = this.also {
	functions.forEach { function { it } }
}

/**
 * This solves some recursion issues in [dynamicEntry].
 *
 * @param id the id of the entry.
 * @author TheOnlyTails
 */
private fun dynamicLootEntry(id: ResourceLocation) = DynamicLoot.dynamicEntry(id)