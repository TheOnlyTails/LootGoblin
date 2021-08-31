package com.theonlytails.lootgoblin

import net.minecraft.advancements.critereon.*
import net.minecraft.core.BlockPos
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.predicates.*
import net.minecraft.world.level.storage.loot.predicates.AlternativeLootItemCondition.alternative
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition.bonusLevelFlatChance
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition.hasDamageSource
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition.survivesExplosion
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition.invert
import net.minecraft.world.level.storage.loot.predicates.LocationCheck.checkLocation
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition.hasBlockStateProperties
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition.entityPresent
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition.hasProperties
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition.killedByPlayer
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition.randomChance
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost
import net.minecraft.world.level.storage.loot.predicates.MatchTool.toolMatches
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition as BlockPropertyCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder as Condition

typealias KilledByPlayerCondition = LootItemKilledByPlayerCondition
typealias EntityPropertyCondition = LootItemEntityPropertyCondition
typealias LootingRandomChanceCondition = LootItemRandomChanceWithLootingCondition
typealias RandomChanceCondition = LootItemRandomChanceCondition
typealias AlternativeCondition = AlternativeLootItemCondition
typealias InvertedCondition = InvertedLootItemCondition

/**
 * Uses an [InvertedCondition] condition to invert another [Condition].
 *
 * @param condition the condition being inverted.
 * @author TheOnlyTails
 */
@LootGoblin
fun inverted(condition: Condition, body: Condition.() -> Condition = { this }) = invert(condition).body()

/**
 * Uses an [AlternativeCondition] condition to choose between multiple [Condition]s.
 *
 * @param conditions the conditions being chosen from.
 * @author TheOnlyTails
 */
@LootGoblin
fun alternative(
	conditions: Collection<Condition>,
	body: Condition.() -> Condition = { this },
) = alternative(*conditions.toTypedArray()).body()

/**
 * Creates a [RandomChanceCondition] condition.
 *
 * @param chance the chance of this condition.
 * @author TheOnlyTails
 */
@LootGoblin
fun randomChance(chance: Float, body: Condition.() -> Condition = { this }) = randomChance(chance).body()

/**
 * Creates a [LootingRandomChanceCondition] condition.
 *
 * @param chance the chance of this condition.
 * @param lootingMultiplier the multiplier of the output if looting is applied.
 * @author TheOnlyTails
 */
@LootGoblin
fun randomChanceWithLooting(
	chance: Float,
	lootingMultiplier: Float,
	body: Condition.() -> Condition = { this },
) = randomChanceAndLootingBoost(chance, lootingMultiplier).body()

/**
 * Creates a [EntityPropertyCondition] condition.
 *
 * @param target the entity targeted by this condition.
 * @param predicate the [EntityPredicate] that matches against the targeted entity.
 * @author TheOnlyTails
 */
@LootGoblin
fun entityProperties(
	target: LootContext.EntityTarget,
	predicate: EntityPredicate.Builder,
	body: Condition.() -> Condition = { this },
) = hasProperties(target, predicate).body()

/**
 * Creates a [LootItemEntityPropertyCondition] condition.
 *
 * @param target the entity targeted by this condition.
 * @author TheOnlyTails
 */
@LootGoblin
fun entityPresent(target: LootContext.EntityTarget, body: Condition.() -> Condition = { this }) =
	entityPresent(target).body()

/**
 * Creates a [KilledByPlayerCondition] condition.
 *
 * @author TheOnlyTails
 */
@LootGoblin
fun killedByPlayer(body: Condition.() -> Condition = { this }) = killedByPlayer().body()

/**
 * Creates a [BlockPropertyCondition] condition.
 *
 * @param block the block of this condition.
 * @author TheOnlyTails
 */
@LootGoblin
fun blockStateProperty(
	block: Block,
	body: BlockPropertyCondition.Builder.() -> BlockPropertyCondition.Builder = { this }
) =
	hasBlockStateProperties(block).body()

/**
 * Creates a [MatchTool] condition.
 *
 * @param predicate the predicate that matches against the tool in this condition.
 * @author TheOnlyTails
 */
@LootGoblin
fun matchTool(predicate: ItemPredicate.Builder, body: Condition.() -> Condition = { this }) =
	toolMatches(predicate).body()

/**
 * Creates a [BonusLevelTableCondition] condition that passes with probability picked from table, indexed by enchantment level.
 *
 * @param enchantment the enchantment being indexed by.
 * @param chances the list of chances for each enchantment level.
 * @author TheOnlyTails
 */

@LootGoblin
fun tableBonus(
	enchantment: Enchantment,
	vararg chances: Float,
	body: Condition.() -> Condition = { this },
) = bonusLevelFlatChance(enchantment, *chances).body()

/**
 * Creates a [ExplosionCondition] condition.
 *
 * @author TheOnlyTails
 */
@LootGoblin
fun survivesExplosion(body: Condition.() -> Condition = { this }) = survivesExplosion().body()

/**
 * Creates a [DamageSourceCondition] condition.
 *
 * @param predicate the [DamageSourcePredicate] that this condition matches against.
 * @author TheOnlyTails
 */
@LootGoblin
fun damageSourceProperties(
	predicate: DamageSourcePredicate.Builder,
	body: Condition.() -> Condition = { this },
) = hasDamageSource(predicate).body()

/**
 * Creates a [LocationCheck] condition.
 *
 * @param pos the position to perform this check on.
 * @param predicate the [LocationPredicate] that this condition matches against.
 * @author TheOnlyTails
 */
@LootGoblin
fun locationCheck(
	pos: BlockPos = BlockPos.ZERO,
	predicate: LocationPredicate.Builder,
	body: Condition.() -> Condition = { this },
) = checkLocation(predicate, pos).body()