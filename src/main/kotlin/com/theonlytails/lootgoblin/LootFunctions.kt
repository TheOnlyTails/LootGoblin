@file:JvmName("LootFunctions")
package com.theonlytails.lootgoblin

import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.loot.IntRange.range
import net.minecraft.world.level.storage.loot.functions.*
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay.explosionDecay
import net.minecraft.world.level.storage.loot.functions.CopyBlockState.copyState
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction.copyName
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction.enchantWithLevels
import net.minecraft.world.level.storage.loot.functions.SetContainerContents.setContents
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction.setCount
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction.setDamage
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction.setTag
import net.minecraft.world.level.storage.loot.functions.SetStewEffectFunction.stewEffect
import net.minecraft.world.level.storage.loot.providers.nbt.NbtProvider
import net.minecraft.world.level.storage.loot.providers.number.*

typealias BinomialRange = BinomialDistributionGenerator
typealias ConditionalFunctionBuilder = LootItemConditionalFunction.Builder<*>

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
fun setCount(value: NumberProvider, body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) =
	setCount(value).body()

/**
 * Creates a [SetItemCountFunction] loot function with a [ConstantValue].
 */
@LootGoblinDsl
fun setConstantCount(value: Float, body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) =
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
	body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }
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
	body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }
) = setCount(uniformGenerator(min, max)).body()

/**
 * Creates an [EnchantWithLevelsFunction] loot function.
 *
 * @param levels the range parameter of the function.
 */
@LootGoblinDsl
fun enchantWithLevels(
	levels: NumberProvider,
	body: EnchantWithLevelsFunction.Builder.() -> EnchantWithLevelsFunction.Builder = { this }
) = enchantWithLevels(levels).body()

/**
 * Creates an [EnchantRandomlyFunction] loot function.
 */
@LootGoblinDsl
fun enchantRandomly(body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) =
	EnchantRandomlyFunction.randomApplicableEnchantment().body()

/**
 * Creates a [SetNbtFunction] loot function.
 *
 * @param tag the [CompoundTag] parameter of the function.
 */
@LootGoblinDsl
fun setNbt(tag: CompoundTag, body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) = setTag(tag).body()

/**
 * Creates a [SmeltItemFunction] loot function.
 */
@LootGoblinDsl
fun furnaceSmelt(body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) = SmeltItemFunction.smelted().body()

/**
 * Creates an [LootingEnchantFunction] loot function.
 *
 * @param value the range parameter of the function.
 */
@LootGoblinDsl
fun looting(
	value: NumberProvider,
	body: LootingEnchantFunction.Builder.() -> LootingEnchantFunction.Builder = { this }
) =
	LootingEnchantFunction.lootingMultiplier(value).body()

/**
 * Creates an [SetItemDamageFunction] loot function.
 *
 * @param damage the range of damage of the function.
 */
@LootGoblinDsl
fun setDamage(damage: NumberProvider, body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) =
	setDamage(damage).body()

/**
 * Creates an [ExplorationMapFunction] loot function.
 */
@LootGoblinDsl
fun explorationMap(body: ExplorationMapFunction.Builder.() -> ExplorationMapFunction.Builder = { this }) =
	ExplorationMapFunction.makeExplorationMap().body()

/**
 * Creates an [SetStewEffectFunction] loot function.
 */
@LootGoblinDsl
fun stewEffect(body: SetStewEffectFunction.Builder.() -> SetStewEffectFunction.Builder = { this }) = stewEffect().body()

/**
 * Creates an [CopyNameFunction] loot function.
 *
 * @param source the type of [CopyNameFunction.NameSource] to copy the name from.
 */
@LootGoblinDsl
fun copyName(source: CopyNameFunction.NameSource, body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) =
	copyName(source).body()

/**
 * Creates an [SetContainerContents] loot function.
 */
@LootGoblinDsl
fun setContents(body: SetContainerContents.Builder.() -> SetContainerContents.Builder = { this }) = setContents().body()

/**
 * Creates an [LimitCount] loot function.
 *
 * @param limiter the limiter of the function.
 */
@Suppress("RemoveRedundantQualifierName")
@LootGoblinDsl
fun limitCount(limiter: IntRange, body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) =
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
	body: LootFunctionBuilder.() -> LootFunctionBuilder = { this },
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
	body: LootFunctionBuilder.() -> LootFunctionBuilder = { this },
) = ApplyBonusCount.addBonusBinomialDistributionCount(enchantment, chance, extraRounds).body()

/**
 * Creates an [ApplyBonusCount] loot function of type [ApplyBonusCount.addOreBonusCount] with the [ApplyBonusCount.OreDrops].
 *
 * @param enchantment the [Enchantment] of the function.
 */
@LootGoblinDsl
fun oreBonusCount(enchantment: Enchantment, body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) =
	ApplyBonusCount.addOreBonusCount(enchantment).body()

/**
 * Creates an [ApplyExplosionDecay] loot function.
 */
@LootGoblinDsl
fun explosionDecay(body: LootFunctionBuilder.() -> LootFunctionBuilder = { this }) = explosionDecay().body()

/**
 * Creates an [CopyNbtFunction] loot function.
 *
 * @param source the type of [NbtProvider] to copy the [CompoundTag] from.
 */
@LootGoblinDsl
fun copyNbt(source: NbtProvider, body: CopyNbtFunction.Builder.() -> CopyNbtFunction.Builder = { this }) =
	CopyNbtFunction.copyData(source).body()

/**
 * Creates an [CopyBlockState] loot function.
 *
 * @param block the [Block] to copy the [BlockState] from.
 */
@LootGoblinDsl
fun copyState(block: Block, body: CopyBlockState.Builder.() -> CopyBlockState.Builder = { this }) =
	copyState(block).body()