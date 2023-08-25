package com.ymotpyrc.swoops.presentation

import com.ymotpyrc.swoops.domain.games.models.PlayerId
import com.ymotpyrc.swoops.domain.stats.models.PlayerStats

class PlayerStatsUiMapper {

    fun map(playerId: PlayerId, playerStats: PlayerStats): PlayerStatsUi =
        PlayerStatsUi(
            playerId = playerId,
            played = playerStats.played.value,
            wins = playerStats.wins.value,
            losses = playerStats.losses.value,
            goals = playerStats.goals.value,
            fieldGoals = playerStats.fieldGoals.value,
            fieldGoalAttempts = playerStats.fieldGoalAttempts.value,
            threePoints = playerStats.threePoints.value,
            threePointAttempts = playerStats.threePointAttempts.value,
            twoPoints = playerStats.twoPoints.value,
            twoPointAttempts = playerStats.twoPointAttempts.value,
            freeThrows = playerStats.freeThrows.value,
            freeThrowAttempts = playerStats.freeThrowAttempts.value,
            defensiveRebounds = playerStats.defensiveRebounds.value,
            offensiveRebounds = playerStats.offensiveRebounds.value,
            rebounds = playerStats.rebounds.value,
            assists = playerStats.assists.value,
            steals = playerStats.steals.value,
            blocks = playerStats.blocks.value,
            fouls = playerStats.fouls.value,
            points = playerStats.points.value,
            turnovers = playerStats.turnovers.value,
            fieldGoalsPerGame = getPerGame(playerStats.fieldGoals.value, playerStats.played.value),
            fieldGoalAttemptsPerGame = getPerGame(playerStats.fieldGoalAttempts.value, playerStats.played.value),
            fieldGoalPercentagePerGame = getPercentage(playerStats.fieldGoals.value, playerStats.fieldGoalAttempts.value),
            threePointsPerGame = getPerGame(playerStats.threePoints.value, playerStats.played.value),
            threePointAttemptsPerGame = getPerGame(playerStats.threePointAttempts.value, playerStats.played.value),
            threePointPercentagePerGame = getPercentage(playerStats.threePoints.value, playerStats.threePointAttempts.value),
            twoPointsPerGame = getPerGame(playerStats.twoPoints.value, playerStats.played.value),
            twoPointAttemptsPerGame = getPerGame(playerStats.twoPointAttempts.value, playerStats.played.value),
            twoPointPercentagePerGame = getPercentage(playerStats.twoPoints.value, playerStats.twoPointAttempts.value),
            freeThrowsPerGame = getPerGame(playerStats.freeThrows.value, playerStats.played.value),
            freeThrowAttemptsPerGame = getPerGame(playerStats.freeThrowAttempts.value, playerStats.played.value),
            freeThrowPercentagePerGame = getPercentage(playerStats.freeThrows.value, playerStats.freeThrowAttempts.value),
            defensiveReboundsPerGame = getPerGame(playerStats.defensiveRebounds.value, playerStats.played.value),
            offensiveReboundsPerGame = getPerGame(playerStats.offensiveRebounds.value, playerStats.played.value),
            reboundsPerGame = getPerGame(playerStats.rebounds.value, playerStats.played.value),
            assistsPerGame = getPerGame(playerStats.assists.value, playerStats.played.value),
            stealsPerGame = getPerGame(playerStats.steals.value, playerStats.played.value),
            blocksPerGame = getPerGame(playerStats.blocks.value, playerStats.played.value),
            foulsPerGame = getPerGame(playerStats.fouls.value, playerStats.played.value),
            pointsPerGame = getPerGame(playerStats.points.value, playerStats.played.value),
            turnoversPerGame = getPerGame(playerStats.turnovers.value, playerStats.played.value),
            trueShootingPercentage = getTrueShootingPercentage(playerStats.points.value, playerStats.fieldGoalAttempts.value, playerStats.freeThrowAttempts.value)
        )

    private fun getTrueShootingPercentage(points: Int, fieldGoalAttempts: Int, freeThrowAttempts: Int): Float =
        points.toFloat() / (2 * (fieldGoalAttempts + 0.44 * freeThrowAttempts)).toFloat()

    private fun getPerGame(value: Int, played: Int): Float = value.toFloat() / played

    private fun getPercentage(scored: Int, total: Int): Float = scored.toFloat() / total
}
