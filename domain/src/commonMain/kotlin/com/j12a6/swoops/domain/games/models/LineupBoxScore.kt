package com.j12a6.swoops.domain.games.models

data class LineupBoxScore(
    val id: Int,
    val assists: Int,
    val blocks: Int,
    val defensiveRebounds: Int,
    val fieldGoals: Int,
    val fieldGoalPercentage: Float,
    val fieldGoalAttempts: Int,
    val freeThrows: Int,
    val freeThrowPercentage: Float,
    val freeThrowAttempts: Int,
    val offensiveRebounds: Int,
    val personalFouls: Int,
    val points: Int,
    val steals: Int,
    val threePoints: Int,
    val threePointPercentage: Float,
    val threePointAttempts: Int,
    val turnovers: Int,
    val totalRebounds: Int,
    val twoPoints: Int,
    val twoPointPercentage: Float,
    val twoPointAttempts: Int,
)