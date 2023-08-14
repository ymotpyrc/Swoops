package com.j12a6.swoops.domain.players.models

data class PlayerStats(
    val played : Int,
    val wins: Int,
    val losses: Int,
    val token: Int,
    val fullName: String,
    val g: Int,
    val fieldGoals: Int,
    val fieldGoalAttempts: Int,
    val threePoints: Int,
    val threePointAttempts: Int,
    val twoPoints: Int,
    val twoPointAttempts: Int,
    val freeThrows: Int,
    val freeThrowAttempts: Int,
    val defensiveRebounds: Int,
    val offensiveRebounds: Int,
    val rebounds: Int,
    val assists: Int,
    val steals: Int,
    val blocks: Int,
    val fouls: Int,
    val points: Int,
    val turnovers: Int,
)
