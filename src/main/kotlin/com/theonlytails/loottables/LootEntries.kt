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
): StandaloneLootEntry.Builder<*> = ItemLootEntry.lootTableItem(item).also { add(it) }

@LootTablesDsl
fun LootPool.Builder.itemEntry(
	item: IItemProvider,
	body: StandaloneLootEntry.Builder<*>.() -> LootEntry.Builder<*>,
) = itemEntry(item).body().also { add(it) }

@LootTablesDsl
fun LootPool.Builder.tagEntry(
	tag: ITag<Item>,
): StandaloneLootEntry.Builder<*> = TagLootEntry.expandTag(tag).also { add(it) }

@LootTablesDsl
fun LootPool.Builder.tagEntry(
	tag: ITag<Item>,
	body: StandaloneLootEntry.Builder<*>.() -> LootEntry.Builder<*>,
) = tagEntry(tag).body().also { add(it) }

@LootTablesDsl
fun LootPool.Builder.tableEntry(
	lootTable: ResourceLocation,
): StandaloneLootEntry.Builder<*> = TableLootEntry.lootTableReference(lootTable).also { add(it) }

@LootTablesDsl
fun LootPool.Builder.tableEntry(
	lootTable: ResourceLocation,
	body: StandaloneLootEntry.Builder<*>.() -> LootEntry.Builder<*>,
) = tableEntry(lootTable).body().also { add(it) }

@LootTablesDsl
fun LootPool.Builder.dynamicEntry(
	id: ResourceLocation,
): StandaloneLootEntry.Builder<*> = DynamicLootEntry.dynamicEntry(id).also { add(it) }

@LootTablesDsl
fun LootPool.Builder.dynamicEntry(
	id: ResourceLocation,
	body: StandaloneLootEntry.Builder<*>.() -> LootEntry.Builder<*>,
) = dynamicEntry(id).body().also { add(it) }

@LootTablesDsl
fun LootPool.Builder.alternativesEntry(
	vararg entries: LootEntry.Builder<*>,
): AlternativesLootEntry.Builder = AlternativesLootEntry.alternatives(*entries).also { add(it) }

@LootTablesDsl
fun LootPool.Builder.alternativesEntry(
	vararg entries: LootEntry.Builder<*>,
	body: AlternativesLootEntry.Builder.() -> AlternativesLootEntry.Builder,
) = alternativesEntry(*entries).body().also { add(it) }

@LootTablesDsl
fun LootPool.Builder.emptyEntry(): StandaloneLootEntry.Builder<*> = EmptyLootEntry.emptyItem().also { add(it) }

@LootTablesDsl
fun LootPool.Builder.emptyEntry(body: StandaloneLootEntry.Builder<*>.() -> StandaloneLootEntry.Builder<*>) =
	emptyEntry().body().also { add(it) }

@LootTablesDsl
fun LootEntry.Builder<*>.condition(getCondition: () -> ILootCondition.IBuilder): LootEntry.Builder<*> =
	`when`(getCondition())

@LootTablesDsl
fun StandaloneLootEntry.Builder<*>.function(getFunction: () -> ILootFunction.IBuilder): StandaloneLootEntry.Builder<*> =
	apply(getFunction())