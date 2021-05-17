package com.theonlytails.loottables

import net.minecraft.advancements.criterion.*
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.loot.LootContext.EntityTarget
import net.minecraft.loot.conditions.*
import net.minecraft.loot.conditions.Alternative.alternative
import net.minecraft.loot.conditions.BlockStateProperty.hasBlockStateProperties
import net.minecraft.loot.conditions.DamageSourceProperties.hasDamageSource
import net.minecraft.loot.conditions.EntityHasProperty.entityPresent
import net.minecraft.loot.conditions.EntityHasProperty.hasProperties
import net.minecraft.loot.conditions.Inverted.invert
import net.minecraft.loot.conditions.KilledByPlayer.killedByPlayer
import net.minecraft.loot.conditions.LocationCheck.checkLocation
import net.minecraft.loot.conditions.MatchTool.toolMatches
import net.minecraft.loot.conditions.RandomChance.randomChance
import net.minecraft.loot.conditions.RandomChanceWithLooting.randomChanceAndLootingBoost
import net.minecraft.loot.conditions.SurvivesExplosion.survivesExplosion
import net.minecraft.loot.conditions.TableBonus.bonusLevelFlatChance
import net.minecraft.util.math.BlockPos
import net.minecraft.loot.conditions.ILootCondition.IBuilder as Condition

/**
 * Uses an [Inverted] condition to invert another [Condition].
 *
 * @param condition the condition being inverted.
 * @param body the configuration block of this condition.
 * @return the [Inverted] condition.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun inverted(condition: Condition, body: Condition.() -> Condition = { this }) = invert(condition).body()

/**
 * Uses an [Alternative] condition to choose between multiple [Condition].
 *
 * @param conditions the conditions being chosen from.
 * @param body the configuration block of this condition.
 * @return the [Alternative] condition.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun alternative(
	conditions: Collection<Condition>,
	body: Condition.() -> Condition = { this },
) = alternative(*conditions.toTypedArray()).body()

/**
 * Creates a [RandomChance] condition.
 *
 * @param chance the chance of this condition.
 * @param body the configuration block of this condition.
 * @return the [RandomChance] condition.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun randomChance(chance: Float, body: Condition.() -> Condition = { this }) = randomChance(chance).body()

/**
 * Creates a [RandomChanceWithLooting] condition.
 *
 * @param chance the chance of this condition.
 * @param lootingMultiplier the multiplier of the output if looting is applied.
 * @param body the configuration block of this condition.
 * @return the [RandomChanceWithLooting] condition.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun randomChanceWithLooting(
	chance: Float,
	lootingMultiplier: Float,
	body: Condition.() -> Condition = { this },
) = randomChanceAndLootingBoost(chance, lootingMultiplier).body()

/**
 * Creates a [EntityHasProperty] condition.
 *
 * @param target the entity targeted by this condition.
 * @param predicate the [EntityPredicate] that matches against the targeted entity.
 * @param body the configuration block of this condition.
 * @return the [EntityHasProperty] condition.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun entityProperties(
	target: EntityTarget,
	predicate: EntityPredicate.Builder,
	body: Condition.() -> Condition = { this },
) = hasProperties(target, predicate).body()

/**
 * Creates a [EntityHasProperty] condition.
 *
 * @param target the entity targeted by this condition.
 * @param body the configuration block of this condition.
 * @return the [EntityHasProperty] condition.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun entityPresent(target: EntityTarget, body: Condition.() -> Condition = { this }) = entityPresent(target).body()

/**
 * Creates a [KilledByPlayer] condition.
 *
 * @param body the configuration block of this condition.
 * @return the [KilledByPlayer] condition.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun killedByPlayer(body: Condition.() -> Condition = { this }) = killedByPlayer().body()

/**
 * Creates a [BlockStateProperty] condition.
 *
 * @param block the block of this condition.
 * @param body the configuration block of this condition.
 * @return the [BlockStateProperty] condition.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun blockStateProperty(block: Block, body: BlockStateProperty.Builder.() -> BlockStateProperty.Builder = { this }) =
	hasBlockStateProperties(block).body()

/**
 * Creates a [MatchTool] condition.
 *
 * @param predicate the predicate that matches against the tool in this condition.
 * @param body the configuration block of this condition.
 * @return the [MatchTool] condition.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun matchTool(predicate: ItemPredicate.Builder, body: Condition.() -> Condition = { this }) =
	toolMatches(predicate).body()

/**
 * Creates a [TableBonus] condition that passes with probability picked from table, indexed by enchantment level.
 *
 * @param enchantment the enchantment being indexed by.
 * @param chances the list of chances for each enchantment level.
 * @param body the configuration block of this condition.
 * @return the [TableBonus] condition.
 * @author TheOnlyTails
 */

@LootTablesDsl
fun tableBonus(
	enchantment: Enchantment,
	vararg chances: Float,
	body: Condition.() -> Condition = { this },
) = bonusLevelFlatChance(enchantment, *chances).body()

/**
 * Creates a [SurvivesExplosion] condition.
 *
 * @param body the configuration block of this condition.
 * @return the [SurvivesExplosion] condition.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun survivesExplosion(body: Condition.() -> Condition = { this }) = survivesExplosion().body()

/**
 * Creates a [DamageSourceProperties] condition.
 *
 * @param predicate the [DamageSourcePredicate] that this condition matches against.
 * @param body the configuration block of this condition.
 * @return the [DamageSourceProperties] condition.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun damageSourceProperties(
	predicate: DamageSourcePredicate.Builder,
	body: Condition.() -> Condition = { this },
) = hasDamageSource(predicate).body()

/**
 * Creates a [LocationCheck] condition.
 *
 * @param pos the position to perform this check on.
 * @param predicate the [LocationPredicate] that this condition matches against.
 * @param body the configuration block of this condition.
 * @return the [LocationCheck] condition.
 * @author TheOnlyTails
 */
@LootTablesDsl
fun locationCheck(
	pos: BlockPos = BlockPos.ZERO,
	predicate: LocationPredicate.Builder,
	body: Condition.() -> Condition = { this },
) = checkLocation(predicate, pos).body()