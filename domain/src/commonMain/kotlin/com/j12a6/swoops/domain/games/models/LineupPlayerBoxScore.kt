package com.j12a6.swoops.domain.games.models

data class LineupPlayerBoxScore(
    val id: Int,
    val position: Int,
    val assists: Int,
    val blocks: Int,
    val defensiveRebounds: Int,
    val fieldGoals: Int,
    val fieldGoalAttempts: Int,
    val fieldGoalPercentage: Float,
    val freeThrows: Int,
    val freeThrowAttempts: Int,
    val freeThrowPercentage: Float,
    val offensiveRebounds: Int,
    val personalFouls: Int,
    val points: Int,
    val steals: Int,
    val threePoints: Int,
    val threePointAttempts: Int,
    val threePointPercentage: Float,
    val turnovers: Int,
    val totalRebounds: Int,
    val twoPoints: Int,
    val twoPointAttempts: Int,
    val twoPointPercentage: Float,
)