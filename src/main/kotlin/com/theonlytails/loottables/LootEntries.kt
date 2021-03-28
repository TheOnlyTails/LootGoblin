package com.theonlytails.loottables

import net.minecraft.item.Item
import net.minecraft.loot.*
import net.minecraft.loot.conditions.ILootCondition
import net.minecraft.loot.functions.ILootFunction
import net.minecraft.tags.ITag
import net.minecraft.util.IItemProvider
import net.minecraft.util.ResourceLocation

@LootTablesDsl
fun LootPool.Builder.itemEntry(
	item: IItemProvider,
): LootPool.Builder = add(ItemLootEntry.lootTableItem(item))

@LootTablesDsl
fun LootPool.Builder.itemEntry(
	item: IItemProvider,
	body: StandaloneLootEntry.Builder<*>.() -> StandaloneLootEntry.Builder<*>,
): LootPool.Builder = add(ItemLootEntry.lootTableItem(item).body())

@LootTablesDsl
fun LootPool.Builder.tagEntry(
	tag: ITag<Item>,
): LootPool.Builder = add(TagLootEntry.expandTag(tag))

@LootTablesDsl
fun LootPool.Builder.tagEntry(
	tag: ITag<Item>,
	body: StandaloneLootEntry.Builder<*>.() -> StandaloneLootEntry.Builder<*>,
): LootPool.Builder = add(TagLootEntry.expandTag(tag).body())

@LootTablesDsl
fun LootPool.Builder.tableEntry(
	lootTable: ResourceLocation,
): LootPool.Builder = add(TableLootEntry.lootTableReference(lootTable))

@LootTablesDsl
fun LootPool.Builder.tableEntry(
	lootTable: ResourceLocation,
	body: StandaloneLootEntry.Builder<*>.() -> StandaloneLootEntry.Builder<*>,
): LootPool.Builder = add(TableLootEntry.lootTableReference(lootTable).body())

@LootTablesDsl
fun LootPool.Builder.dynamicEntry(
	id: ResourceLocation,
): LootPool.Builder = add(DynamicLootEntry.dynamicEntry(id))

@LootTablesDsl
fun LootPool.Builder.dynamicEntry(
	id: ResourceLocation,
	body: StandaloneLootEntry.Builder<*>.() -> StandaloneLootEntry.Builder<*>,
): LootPool.Builder = add(DynamicLootEntry.dynamicEntry(id).body())

@LootTablesDsl
fun LootPool.Builder.alternativesEntry(
	vararg entries: LootEntry.Builder<*>,
): LootPool.Builder = add(AlternativesLootEntry.alternatives(*entries))

@LootTablesDsl
fun LootPool.Builder.alternativesEntry(
	vararg entries: LootEntry.Builder<*>,
	body: AlternativesLootEntry.Builder.() -> AlternativesLootEntry.Builder,
): LootPool.Builder = add(AlternativesLootEntry.alternatives(*entries).body())

@LootTablesDsl
fun LootPool.Builder.emptyEntry(): LootPool.Builder = add(EmptyLootEntry.emptyItem())

@LootTablesDsl
fun LootPool.Builder.emptyEntry(body: StandaloneLootEntry.Builder<*>.() -> StandaloneLootEntry.Builder<*>): LootPool.Builder =
	add(EmptyLootEntry.emptyItem().body())

@LootTablesDsl
fun LootEntry.Builder<*>.condition(getCondition: () -> ILootCondition.IBuilder): LootEntry.Builder<*> =
	`when`(getCondition())

@LootTablesDsl
fun StandaloneLootEntry.Builder<*>.function(getFunction: () -> ILootFunction.IBuilder): LootEntry.Builder<*> =
	apply(getFunction())