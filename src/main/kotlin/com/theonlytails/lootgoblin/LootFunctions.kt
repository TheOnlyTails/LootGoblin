@file:JvmName("LootFunctions")
package com.theonlytails.lootgoblin

import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.loot.IntRange.range
import net.minecraft.world.level.storage.loot.functions.*
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay.explosionDecay
import net.minecraft.world.level.storage.loot.functions.CopyBlockState.copyState
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction.copyName
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction.enchantWithLevels
import net.minecraft.world.level.storage.loot.functions.SetContainerContents.setContents
import net.minecraft.world.level.storage.loot.functions.SetContainerLootTable.withLootTable
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction.setCount
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction.setDamage
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction.setTag
import net.minecraft.world.level.storage.loot.functions.SetStewEffectFunction.stewEffect
import net.minecraft.world.level.storage.loot.providers.nbt.NbtProvider
import net.minecraft.world.level.storage.loot.providers.number.*

typealias BinomialRange = BinomialDistributionGenerator
typealias ConditionalFunctionBuilder = LootItemConditionalFunction.Builder<*>

typealias LootFunctionBuilderBody = LootFunctionBuilder.() -> LootFunctionBuilder
typealias LootFunctionBody<T> = LootItemConditionalFunction.Builder<T>.() -> LootItemConditionalFunction.Builder<T>

/**
 * Adds a [LootConditionBuilder] to a [ConditionalFunctionBuilder].
 */
@LootGoblinDsl
fun ConditionalFunctionBuilder.condition(getLootConditionBuilder: () -> LootConditionBuilder) =
	`when`(getLootConditionBuilder())
		?: throw LootTableCreationException("Something went wrong while adding a condition to a function")

/**
 * Creates a [SetItemCountFunction] loot function.
 *
 * @param value the range parameter of the function.
 */
@LootGoblinDsl
fun setCount(value: NumberProvider, body: LootFunctionBuilderBody = { this }) =
	setCount(value).body()

/**
 * Creates a [SetItemCountFunction] loot function with a [ConstantValue].
 */
@LootGoblinDsl
fun setConstantCount(value: Float, body: LootFunctionBuilderBody = { this }) =
	setCount(constantValue(value)).body()

/**
 * Creates a [SetItemCountFunction] loot function with a [BinomialRange].
 *
 * @param amount the amount of trails in the range.
 * @param chance the chance of success in a trail.
 */
@LootGoblinDsl
fun setBinomialCount(
	amount: Int,
	chance: Float,
	body: LootFunctionBuilderBody = { this },
) = setCount(binomialRange(amount, chance)).body()

/**
 * Creates a [SetItemCountFunction] loot function with a [UniformGenerator].
 *
 * @param min the lower bound of the range.
 * @param max the upper bound of the range.
 */
@LootGoblinDsl
fun setUniformCount(
	min: Float,
	max: Float,
	body: LootFunctionBuilderBody = { this },
) = setCount(uniformGenerator(min, max)).body()

/**
 * Creates an [EnchantWithLevelsFunction] loot function.
 *
 * @param levels the range parameter of the function.
 */
@LootGoblinDsl
fun enchantWithLevels(
	levels: NumberProvider,
	body: LootFunctionBody<EnchantWithLevelsFunction.Builder> = { this },
) = enchantWithLevels(levels).body()

/**
 * Creates an [EnchantRandomlyFunction] loot function.
 */
@LootGoblinDsl
fun enchantRandomly(body: LootFunctionBuilderBody = { this }) =
	EnchantRandomlyFunction.randomApplicableEnchantment().body()

/**
 * Creates a [SetNbtFunction] loot function.
 *
 * @param tag the [CompoundTag] parameter of the function.
 */
@Deprecated("Set for removal in a future Minecraft version.", level = DeprecationLevel.HIDDEN)
@LootGoblinDsl
fun setNbt(tag: CompoundTag, body: LootFunctionBuilderBody = { this }) = setTag(tag).body()

/**
 * Creates a [SmeltItemFunction] loot function.
 */
@LootGoblinDsl
fun furnaceSmelt(body: LootFunctionBuilderBody = { this }) = SmeltItemFunction.smelted().body()

/**
 * Creates an [LootingEnchantFunction] loot function.
 *
 * @param value the range parameter of the function.
 */
@LootGoblinDsl
fun looting(
	value: NumberProvider,
	body: LootFunctionBody<LootingEnchantFunction.Builder> = { this },
) =
	LootingEnchantFunction.lootingMultiplier(value).body()

/**
 * Creates an [SetItemDamageFunction] loot function.
 *
 * @param damage the range of damage of the function.
 */
@LootGoblinDsl
fun setDamage(damage: NumberProvider, body: LootFunctionBuilderBody = { this }) =
	setDamage(damage).body()

/**
 * Creates an [ExplorationMapFunction] loot function.
 */
@LootGoblinDsl
fun explorationMap(body: LootFunctionBody<ExplorationMapFunction.Builder> = { this }) =
	ExplorationMapFunction.makeExplorationMap().body()

/**
 * Creates an [SetStewEffectFunction] loot function.
 */
@LootGoblinDsl
fun stewEffect(body: LootFunctionBody<SetStewEffectFunction.Builder> = { this }) = stewEffect().body()

/**
 * Creates an [CopyNameFunction] loot function.
 *
 * @param source the type of [CopyNameFunction.NameSource] to copy the name from.
 */
@LootGoblinDsl
fun copyName(source: CopyNameFunction.NameSource, body: LootFunctionBuilderBody = { this }) =
	copyName(source).body()

/**
 * Creates an [SetContainerContents] loot function.
 */
@LootGoblinDsl
fun setContents(blockEntityType: BlockEntityType<*>, body: LootFunctionBody<SetContainerContents.Builder> = { this }) =
	setContents(blockEntityType).body()

/**
 * Creates an [SetContainerLootTable] loot function.
 *
 * @param name Specifies the [ResourceLocation] of the loot table to be used.
 * @param type the type to be written in `BlockEntityTag.id`.
 * @param seed Specifies the loot table seed. If absent, a random seed will be used.
 */
@LootGoblinDsl
fun setLootTable(
	name: ResourceLocation,
	type: BlockEntityType<*>,
	seed: Long? = null,
	body: LootFunctionBuilderBody = { this },
) =
	(if (seed != null) withLootTable(type, name, seed) else withLootTable(type, name)).body()

/**
 * Creates an [SetPotionFunction] loot function.
 */
@LootGoblinDsl
fun setPotion(potion: Potion, body: LootFunctionBuilderBody = { this }) = SetPotionFunction.setPotion(potion).body()

/**
 * Creates an [LimitCount] loot function.
 *
 * @param limiter the limiter of the function.
 */
@Suppress("RemoveRedundantQualifierName")
@LootGoblinDsl
fun limitCount(limiter: IntRange, body: LootFunctionBuilderBody = { this }) =
	LimitCount.limitCount(range(limiter.first, limiter.last)).body()

/**
 * Creates an [ApplyBonusCount] loot function of type [ApplyBonusCount.addUniformBonusCount].
 *
 * @param enchantment the enchantment of the function.
 * @param bonusMultiplier the bonus multiplier passed to the [ApplyBonusCount.UniformBonusCount] of the function
 */
@LootGoblinDsl
fun uniformBonusCount(
	enchantment: Enchantment,
	bonusMultiplier: Int = 1,
	body: LootFunctionBuilderBody = { this },
) = ApplyBonusCount.addUniformBonusCount(enchantment, bonusMultiplier).body()

/**
 * Creates an [ApplyBonusCount] loot function of type [ApplyBonusCount.addBonusBinomialDistributionCount].
 *
 * @param enchantment the enchantment of the function.
 * @param chance the chance parameter passed to the [ApplyBonusCount.BinomialWithBonusCount] of the function.
 * @param extraRounds the extra rounds parameter passed to the [ApplyBonusCount.BinomialWithBonusCount] of the function.
 */
@LootGoblinDsl
fun bonusBinomialDistributionCount(
	enchantment: Enchantment,
	chance: Float,
	extraRounds: Int,
	body: LootFunctionBuilderBody = { this },
) = ApplyBonusCount.addBonusBinomialDistributionCount(enchantment, chance, extraRounds).body()

/**
 * Creates an [ApplyBonusCount] loot function of type [ApplyBonusCount.addOreBonusCount] with the [ApplyBonusCount.OreDrops].
 *
 * @param enchantment the [Enchantment] of the function.
 */
@LootGoblinDsl
fun oreBonusCount(enchantment: Enchantment, body: LootFunctionBuilderBody = { this }) =
	ApplyBonusCount.addOreBonusCount(enchantment).body()

/**
 * Creates an [ApplyExplosionDecay] loot function.
 */
@LootGoblinDsl
fun explosionDecay(body: LootFunctionBuilderBody = { this }) = explosionDecay().body()

/**
 * Creates an [CopyNbtFunction] loot function.
 *
 * @param source the type of [NbtProvider] to copy the [CompoundTag] from.
 */
@LootGoblinDsl
fun copyNbt(source: NbtProvider, body: LootFunctionBody<CopyNbtFunction.Builder> = { this }) =
	CopyNbtFunction.copyData(source).body()

/**
 * Creates an [CopyBlockState] loot function.
 *
 * @param block the [Block] to copy the [BlockState] from.
 */
@LootGoblinDsl
fun copyState(block: Block, body: LootFunctionBody<CopyBlockState.Builder> = { this }) =
	copyState(block).body()