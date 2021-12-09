@file:JvmName("LootPredicates")
package com.theonlytails.lootgoblin

import net.minecraft.advancements.critereon.*
import net.minecraft.nbt.CompoundTag
import net.minecraft.tags.Tag
import net.minecraft.world.item.Item
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.Enchantments.SILK_TOUCH
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.predicates.MatchTool

/**
 * An [MatchTool] condition that checks if the breaking tool has [SILK_TOUCH] applied.
 */
@LootGoblinDsl
val hasSilkTouch = matchTool(itemHasEnchantment(enchantAtLeast(SILK_TOUCH, 1)))

/**
 * A [MatchTool] condition that checks if the breaking tool has [SILK_TOUCH] applied.
 */
@Deprecated(
	message = "use the property version of this function.",
	replaceWith = ReplaceWith("hasSilkTouch")
)
@LootGoblinDsl
fun hasSilkTouch() = matchTool(itemHasEnchantment(enchantAtLeast(SILK_TOUCH, 1)))

/**
 * Creates a [StatePropertiesPredicate].
 */
@LootGoblinDsl
fun stateProperties(body: StatePropertiesPredicate.Builder.() -> StatePropertiesPredicate.Builder) =
	StatePropertiesPredicate.Builder.properties().body()

/**
 * Creates a [BlockPredicate] that checks for a [Block].
 *
 * @param block the block to check for.
 */
@LootGoblinDsl
fun blockPredicate(
	block: Block,
	body: BlockPredicate.Builder.() -> BlockPredicate.Builder = { this },
) = BlockPredicate.Builder.block().of(block).body()

/**
 * Creates a [BlockPredicate] that checks for an [Tag].
 *
 * @param blockTag the block [Tag] to check for.
 */
@LootGoblinDsl
fun blockPredicate(
	blockTag: Tag<Block>,
	body: BlockPredicate.Builder.() -> BlockPredicate.Builder = { this },
) = BlockPredicate.Builder.block().of(blockTag).body()

/**
 * Creates a [ItemPredicate] that checks for an [ItemLike].
 *
 * @param item the [Item] to check for.
 */
@LootGoblinDsl
fun itemPredicate(
	item: ItemLike,
	body: ItemPredicate.Builder.() -> ItemPredicate.Builder = { this },
) = ItemPredicate.Builder.item().of(item).body()

/**
 * Creates a [ItemPredicate] that checks for an [Tag].
 *
 * @param itemTag the item [Tag] to check for.
 */
@LootGoblinDsl
fun itemPredicate(
	itemTag: Tag<Item>,
	body: ItemPredicate.Builder.() -> ItemPredicate.Builder = { this },
) = ItemPredicate.Builder.item().of(itemTag).body()

/**
 * Creates a [ItemPredicate] that checks for a [CompoundTag].
 *
 * @param tag the [CompoundTag] to check for.
 */
@LootGoblinDsl
fun itemHasNbt(
	tag: CompoundTag,
	body: ItemPredicate.Builder.() -> ItemPredicate.Builder = { this },
) = ItemPredicate.Builder.item().hasNbt(tag).body()

/**
 * Creates a [ItemPredicate] that checks for a [EnchantmentPredicate].
 *
 * @param enchantment the [EnchantmentPredicate] to check for.
 */
@LootGoblinDsl
fun itemHasEnchantment(
	enchantment: EnchantmentPredicate,
	body: ItemPredicate.Builder.() -> ItemPredicate.Builder = { this },
) = ItemPredicate.Builder.item().hasEnchantment(enchantment).body()

/**
 * Creates a [EnchantmentPredicate] that checks for a lower bound of an [Enchantment].
 *
 * @param enchantment the [Enchantment] to check for.
 * @param min the level of the lower bound.
 * @return the predicate.
 */
@LootGoblinDsl
fun enchantAtLeast(enchantment: Enchantment, min: Int) =
	EnchantmentPredicate(enchantment, MinMaxBounds.Ints.atLeast(min))

/**
 * Creates a [EnchantmentPredicate] that checks for an exact level of an [Enchantment].
 *
 * @param enchantment the [Enchantment] to check for.
 * @param level the level of the enchantment.
 */
@LootGoblinDsl
fun enchantExactly(enchantment: Enchantment, level: Int) =
	EnchantmentPredicate(enchantment, MinMaxBounds.Ints.exactly(level))

/**
 * Creates a [FishingHookPredicate] that checks whether the player was fishing in open water (or not).
 *
 * @param isInOpenWater to check if the player was fishing in open water, or if they weren't.
 */
@LootGoblinDsl
fun fishingInOpenWater(isInOpenWater: Boolean) = FishingHookPredicate.inOpenWater(isInOpenWater)
	?: throw LootTableCreationException("Something went wrong while creating a fishing predicate for fishing in open water.")