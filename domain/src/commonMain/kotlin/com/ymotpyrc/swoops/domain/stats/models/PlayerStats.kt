package com.ymotpyrc.swoops.domain.stats.models

import kotlinx.serialization.Serializable

@Serializable
data class PlayerStats(
    val played : Played,
    val wins: Wins,
    val losses: Losses,
    val goals: Goals,
    val fieldGoals: FieldGoals,
    val fieldGoalAttempts: FieldGoalAttempts,
    val threePoints: ThreePoints,
    val threePointAttempts: ThreePointAttempts,
    val twoPoints: TwoPoints,
    val twoPointAttempts: TwoPointAttempts,
    val freeThrows: FreeThrows,
    val freeThrowAttempts: FreeThrowAttempts,
    val defensiveRebounds: DefensiveRebounds,
    val offensiveRebounds: OffensiveRebounds,
    val rebounds: Rebounds,
    val assists: Assists,
    val steals: Steals,
    val blocks: Blocks,
    val fouls: Fouls,
    val points: Points,
    val turnovers: Turnovers,
)
