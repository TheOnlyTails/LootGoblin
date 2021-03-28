package com.theonlytails.loottables

import net.minecraft.loot.LootParameterSet
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootPool.lootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTable.lootTable

@DslMarker
annotation class LootTablesDsl

@LootTablesDsl
fun lootTableBuilder(parameterSet: LootParameterSet, body: LootTable.Builder.() -> LootTable.Builder): LootTable.Builder =
	lootTable().body().setParamSet(parameterSet)

@LootTablesDsl
fun lootTable(parameterSet: LootParameterSet, body: LootTable.Builder.() -> LootTable.Builder): LootTable =
	lootTable().body().setParamSet(parameterSet).build()

@LootTablesDsl
fun LootTable.Builder.pool(rolls: Int = 1, body: LootPool.Builder.() -> LootPool.Builder): LootTable.Builder =
	withPool(lootPool().setRolls(constantRange(rolls)).body())