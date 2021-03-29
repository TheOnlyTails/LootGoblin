package com.theonlytails.loottables

import net.minecraft.advancements.criterion.*
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.Enchantments.SILK_TOUCH
import net.minecraft.item.Item
import net.minecraft.loot.conditions.ILootCondition
import net.minecraft.loot.conditions.MatchTool
import net.minecraft.nbt.CompoundNBT
import net.minecraft.tags.ITag
import net.minecraft.util.IItemProvider

/**
 * A utility function for a [MatchTool] condition that checks if a tool has the [SILK_TOUCH] enchantment applied.
 *
 * @return a [ILootCondition.IBuilder] that checks if the breaking tool has silk touch applied.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun hasSilkTouch(): ILootCondition.IBuilder = matchTool(itemHasEnchantment(enchantAtLeast(SILK_TOUCH, 1)))

/**
 * Creates a [StatePropertiesPredicate].
 *
 * @param body a block of code that configures the predicate.
 * @return the predicate itself.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun stateProperties(body: StatePropertiesPredicate.Builder.() -> StatePropertiesPredicate.Builder) =
	StatePropertiesPredicate.Builder.properties().body()

/**
 * Creates a [BlockPredicate] that checks for a [Block].
 *
 * @param block the block to check for.
 * @param body configures the predicate.
 * @return the predicate.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun blockPredicate(
	block: Block,
	body: BlockPredicate.Builder.() -> BlockPredicate.Builder = { this },
): BlockPredicate.Builder = BlockPredicate.Builder.block().of(block).body()

/**
 * Creates a [BlockPredicate] that checks for an [ITag].
 *
 * @param blockTag the block [ITag] to check for.
 * @param body configures the predicate.
 * @return the predicate.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun blockPredicate(
	blockTag: ITag<Block>,
	body: BlockPredicate.Builder.() -> BlockPredicate.Builder = { this },
): BlockPredicate.Builder = BlockPredicate.Builder.block().of(blockTag).body()

/**
 * Creates a [ItemPredicate] that checks for an [IItemProvider].
 *
 * @param item the item to check for.
 * @param body configures the predicate.
 * @return the predicate.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun itemPredicate(
	item: IItemProvider,
	body: ItemPredicate.Builder.() -> ItemPredicate.Builder = { this },
): ItemPredicate.Builder = ItemPredicate.Builder.item().of(item).body()

/**
 * Creates a [ItemPredicate] that checks for an [ITag].
 *
 * @param itemTag the item [ITag] to check for.
 * @param body configures the predicate.
 * @return the predicate.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun itemPredicate(
	itemTag: ITag<Item>,
	body: ItemPredicate.Builder.() -> ItemPredicate.Builder = { this },
): ItemPredicate.Builder = ItemPredicate.Builder.item().of(itemTag).body()

/**
 * Creates a [ItemPredicate] that checks for a [CompoundNBT].
 *
 * @param tag the [CompoundNBT] to check for.
 * @param body configures the predicate.
 * @return the predicate.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun itemHasNbt(
	tag: CompoundNBT,
	body: ItemPredicate.Builder.() -> ItemPredicate.Builder = { this },
): ItemPredicate.Builder =
	ItemPredicate.Builder.item().hasNbt(tag)

/**
 * Creates a [ItemPredicate] that checks for a [EnchantmentPredicate].
 *
 * @param enchantment the [EnchantmentPredicate] to check for.
 * @param body configures the predicate.
 * @return the predicate.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun itemHasEnchantment(
	enchantment: EnchantmentPredicate,
	body: ItemPredicate.Builder.() -> ItemPredicate.Builder = { this },
): ItemPredicate.Builder =
	ItemPredicate.Builder.item().hasEnchantment(enchantment)

/**
 * Creates a [EnchantmentPredicate] that checks for a lower bound of an [Enchantment].
 *
 * @param enchantment the [Enchantment] to check for.
 * @param min the level of the lower bound.
 * @return the predicate.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun enchantAtLeast(enchantment: Enchantment, min: Int) =
	EnchantmentPredicate(enchantment, MinMaxBounds.IntBound.atLeast(min))

/**
 * Creates a [EnchantmentPredicate] that checks for an exact of an [Enchantment].
 *
 * @param enchantment the [Enchantment] to check for.
 * @param level the level of the enchantment.
 * @return the predicate.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun enchantExactly(enchantment: Enchantment, level: Int) =
	EnchantmentPredicate(enchantment, MinMaxBounds.IntBound.exactly(level))