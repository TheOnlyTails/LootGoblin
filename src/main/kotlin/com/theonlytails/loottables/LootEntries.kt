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
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.itemEntry(
    item: IItemProvider,
    weight: Int = 1,
    quality: Int = 0,
    addToPool: Boolean = true,
    body: StandaloneEntry<*>.() -> Entry<*> = { this },
) = lootTableItem(item).also { it.setWeight(weight).setQuality(quality).body(); if (addToPool) this.add(it) }
    ?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: item")

/**
 * Adds a [TagLootEntry] to a [Pool].
 *
 * @param tag the [ITag] of the entry.
 * @param addToPool controls whether this entry should be added to the pool.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.tagEntry(
    tag: ITag<Item>,
    weight: Int = 1,
    quality: Int = 0,
    addToPool: Boolean = true,
    body: StandaloneEntry<*>.() -> Entry<*> = { this },
) = TagLootEntry.expandTag(tag).also { it.setWeight(weight).setQuality(quality).body(); if (addToPool) this.add(it) }
    ?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: tag")

/**
 * Adds a [TableLootEntry] to a [Pool].
 *
 * @param lootTable the loot table being referenced in the entry.
 * @param addToPool controls whether this entry should be added to the pool.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.tableEntry(
    lootTable: ResourceLocation,
    weight: Int = 1,
    quality: Int = 0,
    addToPool: Boolean = true,
    body: StandaloneEntry<*>.() -> Entry<*> = { this },
) = TableLootEntry.lootTableReference(lootTable)
    .also { it.setWeight(weight).setQuality(quality).body(); if (addToPool) this.add(it) }
    ?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: loot_table")

/**
 * Adds a [DynamicLootEntry] to a [Pool].
 *
 * @param id the id of the entry.
 * @param addToPool controls whether this entry should be added to the pool.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.dynamicEntry(
    id: ResourceLocation,
    weight: Int = 1,
    quality: Int = 0,
    addToPool: Boolean = true,
    body: StandaloneEntry<*>.() -> Entry<*> = { this },
) = dynamicLootEntry(id).also { it.setWeight(weight).setQuality(quality).body(); if (addToPool) this.add(it) }
    ?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: dynamic")

/**
 * Adds an [AlternativesLootEntry] to a [Pool].
 *
 * @param entries the sub-entries of the entry.
 * @param addToPool controls whether this entry should be added to the pool.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.alternativesEntry(
    vararg entries: Entry<*>,
    addToPool: Boolean = true,
    body: AlternativesLootEntry.Builder.() -> AlternativesLootEntry.Builder = { this },
) = AlternativesLootEntry.alternatives(*entries).also { it.body(); if (addToPool) this.add(it) }
    ?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: alternatives")

/**
 * Adds an [EmptyLootEntry] to a [Pool].
 *
 * @param addToPool controls whether this entry should be added to the pool.
 * @param body a block of code the configures the entry.
 * @return the entry.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Pool.emptyEntry(
    weight: Int = 1,
    quality: Int = 0,
    addToPool: Boolean = true,
    body: StandaloneEntry<*>.() -> Entry<*> = { this },
) = emptyItem().also { it.setWeight(weight).setQuality(quality).body(); if (addToPool) this.add(it) }
    ?: throw LootTableCreationException("Something went wrong while creating a Loot Entry of type: empty")

/**
 * Adds a condition to an [Entry].
 *
 * @receiver an [Entry].
 * @param getCondition a lambda that returns the condition.
 * @return the original entry, with the condition added.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Entry<*>.condition(getCondition: () -> ILootCondition.IBuilder) = `when`(getCondition())
    ?: throw LootTableCreationException("Something went wrong while adding a condition to a loot entry")

/**
 * Adds a function to a [StandaloneEntry].
 *
 * @receiver a [StandaloneEntry].
 * @param getFunction a lambda that returns the function.
 * @return the original entry, with the function added.
 * @throws [LootTableCreationException] if the entry returned is `null`.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun StandaloneEntry<*>.function(getFunction: () -> ILootFunction.IBuilder) = apply(getFunction())
    ?: throw LootTableCreationException("Something went wrong while adding a function to a loot entry")

/**
 * Adds a list of conditions to a [Pool].
 *
 * @receiver a [Pool].
 * @param conditions a list of conditions.
 * @return the original pool, with the conditions added.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun Entry<*>.condition(vararg conditions: ILootCondition.IBuilder) = this.also {
    conditions.forEach { condition { it } }
}

/**
 * Adds a list of functions to a [Pool].
 *
 * @receiver a [Pool].
 * @param functions a list of functions.
 * @return the original pool, with the function added.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun StandaloneEntry<*>.function(vararg functions: ILootFunction.IBuilder) = this.also {
    functions.forEach { function { it } }
}

/**
 * This solves some recursion issues in [dynamicEntry].
 *
 * @param id the id of the entry.
 * @return the entry.
 * @author TheOnlyTails
 */
private fun dynamicLootEntry(id: ResourceLocation) = DynamicLootEntry.dynamicEntry(id)