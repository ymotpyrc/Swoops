package com.ymotpyrc.swoops.domain.stats.models

import kotlinx.serialization.Serializable

@Serializable
data class Played(val value: Int)
@Serializable
data class Wins(val value: Int)
@Serializable
data class Losses(val value: Int)
@Serializable
data class Goals(val value: Int)
@Serializable
data class FieldGoals(val value: Int)
@Serializable
data class FieldGoalAttempts(val value: Int)
@Serializable
data class ThreePoints(val value: Int)
@Serializable
data class ThreePointAttempts(val value: Int)
@Serializable
data class TwoPoints(val value: Int)
@Serializable
data class TwoPointAttempts(val value: Int)
@Serializable
data class FreeThrows(val value: Int)
@Serializable
data class FreeThrowAttempts(val value: Int)
@Serializable
data class DefensiveRebounds(val value: Int)
@Serializable
data class OffensiveRebounds(val value: Int)
@Serializable
data class Rebounds(val value: Int)
@Serializable
data class Assists(val value: Int)
@Serializable
data class Steals(val value: Int)
@Serializable
data class Blocks(val value: Int)
@Serializable
data class Fouls(val value: Int)
@Serializable
data class Points(val value: Int)
@Serializable
data class Turnovers(val value: Int)

operator fun Played.plus(played: Int) = Played(value + played)
operator fun Wins.plus(wins: Int) = Wins(value + wins)
operator fun Losses.plus(losses: Int) = Losses(value + losses)
operator fun Goals.plus(goals: Int) = Goals(value + goals)
operator fun FieldGoals.plus(fieldGoals: Int) = FieldGoals(value + fieldGoals)
operator fun FieldGoalAttempts.plus(fieldGoalAttempts: Int) = FieldGoalAttempts(value + fieldGoalAttempts)
operator fun ThreePoints.plus(threePoints: Int) = ThreePoints(value + threePoints)
operator fun ThreePointAttempts.plus(threePointAttempts: Int) = ThreePointAttempts(value + threePointAttempts)
operator fun TwoPoints.plus(twoPoints: Int) = TwoPoints(value + twoPoints)
operator fun TwoPointAttempts.plus(twoPointAttempts: Int) = TwoPointAttempts(value + twoPointAttempts)
operator fun FreeThrows.plus(freeThrows: Int) = FreeThrows(value + freeThrows)
operator fun FreeThrowAttempts.plus(freeThrowAttempts: Int) = FreeThrowAttempts(value + freeThrowAttempts)
operator fun DefensiveRebounds.plus(defensiveRebounds: Int) = DefensiveRebounds(value + defensiveRebounds)
operator fun OffensiveRebounds.plus(offensiveRebounds: Int) = OffensiveRebounds(value + offensiveRebounds)
operator fun Rebounds.plus(rebounds: Int) = Rebounds(value + rebounds)
operator fun Assists.plus(assists: Int) = Assists(value + assists)
operator fun Steals.plus(steals: Int) = Steals(value + steals)
operator fun Blocks.plus(blocks: Int) = Blocks(value + blocks)
operator fun Fouls.plus(fouls: Int) = Fouls(value + fouls)
operator fun Points.plus(points: Int) = Points(value + points)
operator fun Turnovers.plus(turnovers: Int) = Turnovers(value + turnovers)