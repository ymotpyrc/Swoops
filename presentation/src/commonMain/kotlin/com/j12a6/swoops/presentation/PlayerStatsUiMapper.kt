package com.j12a6.swoops.presentation

import com.j12a6.swoops.domain.players.models.PlayerStats

class PlayerStatsUiMapper {

    fun map(playerStats: PlayerStats): PlayerStatsUi =
        PlayerStatsUi(
            played = playerStats.played,
            wins = playerStats.wins,
            losses = playerStats.losses,
            token = playerStats.token,
            fullName = playerStats.fullName,
            g = playerStats.g,
            fieldGoals = playerStats.fieldGoals,
            fieldGoalAttempts = playerStats.fieldGoalAttempts,
            threePoints = playerStats.threePoints,
            threePointAttempts = playerStats.threePointAttempts,
            twoPoints = playerStats.twoPoints,
            twoPointAttempts = playerStats.twoPointAttempts,
            freeThrows = playerStats.freeThrows,
            freeThrowAttempts = playerStats.freeThrowAttempts,
            defensiveRebounds = playerStats.defensiveRebounds,
            offensiveRebounds = playerStats.offensiveRebounds,
            rebounds = playerStats.rebounds,
            assists = playerStats.assists,
            steals = playerStats.steals,
            blocks = playerStats.blocks,
            fouls = playerStats.fouls,
            points = playerStats.points,
            turnovers = playerStats.turnovers,
            fieldGoalsPerGame = getPerGame(playerStats.fieldGoals, playerStats.played),
            fieldGoalAttemptsPerGame = getPerGame(playerStats.fieldGoalAttempts, playerStats.played),
            fieldGoalPercentagePerGame = getPercentage(playerStats.fieldGoals, playerStats.fieldGoalAttempts),
            threePointsPerGame = getPerGame(playerStats.threePoints, playerStats.played),
            threePointAttemptsPerGame = getPerGame(playerStats.threePointAttempts, playerStats.played),
            threePointPercentagePerGame = getPercentage(playerStats.threePoints, playerStats.threePointAttempts),
            twoPointsPerGame = getPerGame(playerStats.twoPoints, playerStats.played),
            twoPointAttemptsPerGame = getPerGame(playerStats.twoPointAttempts, playerStats.played),
            twoPointPercentagePerGame = getPercentage(playerStats.twoPoints, playerStats.twoPointAttempts),
            freeThrowsPerGame = getPerGame(playerStats.freeThrows, playerStats.played),
            freeThrowAttemptsPerGame = getPerGame(playerStats.freeThrowAttempts, playerStats.played),
            freeThrowPercentagePerGame = getPercentage(playerStats.freeThrows, playerStats.freeThrowAttempts),
            defensiveReboundsPerGame = getPerGame(playerStats.defensiveRebounds, playerStats.played),
            offensiveReboundsPerGame = getPerGame(playerStats.offensiveRebounds, playerStats.played),
            reboundsPerGame = getPerGame(playerStats.rebounds, playerStats.played),
            assistsPerGame = getPerGame(playerStats.assists, playerStats.played),
            stealsPerGame = getPerGame(playerStats.steals, playerStats.played),
            blocksPerGame = getPerGame(playerStats.blocks, playerStats.played),
            foulsPerGame = getPerGame(playerStats.fouls, playerStats.played),
            pointsPerGame = getPerGame(playerStats.points, playerStats.played),
            turnoversPerGame = getPerGame(playerStats.turnovers, playerStats.played),
            trueShootingPercentage = getTrueShootingPercentage(playerStats),
        )

    private fun getTrueShootingPercentage(playerStats: PlayerStats): Float =
        playerStats.points.toFloat() / (2 * (playerStats.fieldGoalAttempts + 0.44 * playerStats.freeThrowAttempts)).toFloat()

    private fun getPerGame(value: Int, played: Int): Float =
        value.toFloat() / played

    private fun getPercentage(scored: Int, total: Int): Float =
        scored.toFloat() / total
}
