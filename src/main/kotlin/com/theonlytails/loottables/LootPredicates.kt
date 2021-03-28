package com.theonlytails.loottables

import net.minecraft.advancements.criterion.*
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.Enchantments.SILK_TOUCH
import net.minecraft.item.Item
import net.minecraft.loot.conditions.ILootCondition
import net.minecraft.nbt.CompoundNBT
import net.minecraft.tags.ITag
import net.minecraft.util.IItemProvider

@LootTablesDsl
fun hasSilkTouch(): ILootCondition.IBuilder = matchTool(itemHasEnchantment(enchantAtLeast(SILK_TOUCH, 1)))

@LootTablesDsl
fun stateProperties(body: StatePropertiesPredicate.Builder.() -> StatePropertiesPredicate.Builder) =
	StatePropertiesPredicate.Builder.properties().body()

@LootTablesDsl
fun blockPredicate(block: Block): BlockPredicate.Builder = BlockPredicate.Builder.block().of(block)

@LootTablesDsl
fun blockPredicate(blockTag: ITag<Block>): BlockPredicate.Builder = BlockPredicate.Builder.block().of(blockTag)

@LootTablesDsl
fun itemPredicate(item: IItemProvider): ItemPredicate.Builder = ItemPredicate.Builder.item().of(item)

@LootTablesDsl
fun itemPredicate(itemTag: ITag<Item>): ItemPredicate.Builder = ItemPredicate.Builder.item().of(itemTag)

@LootTablesDsl
fun itemHasNbt(tag: CompoundNBT): ItemPredicate.Builder = ItemPredicate.Builder.item().hasNbt(tag)

@LootTablesDsl
fun itemHasEnchantment(enchantment: EnchantmentPredicate): ItemPredicate.Builder =
	ItemPredicate.Builder.item().hasEnchantment(enchantment)

@LootTablesDsl
fun enchantAtLeast(enchantment: Enchantment, min: Int) =
	EnchantmentPredicate(enchantment, MinMaxBounds.IntBound.atLeast(min))

@LootTablesDsl
fun enchantExactly(enchantment: Enchantment, level: Int) =
	EnchantmentPredicate(enchantment, MinMaxBounds.IntBound.exactly(level))