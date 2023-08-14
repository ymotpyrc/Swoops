package com.j12a6.swoops.domain.players.usecases

import com.j12a6.swoops.domain.games.models.Game
import com.j12a6.swoops.domain.games.models.LineupPlayerBoxScore
import com.j12a6.swoops.domain.games.models.PlayerId
import com.j12a6.swoops.domain.games.models.TeamId
import com.j12a6.swoops.players.models.PlayerPosition
import com.j12a6.swoops.domain.players.models.PlayerStats
import com.j12a6.swoops.players.models.PlayerWithPosition

class GetPlayersStatsUseCase {

    fun invoke(games: List<Game>): MutableMap<PlayerWithPosition, PlayerStats> {
        val stats = mutableMapOf<PlayerWithPosition, PlayerStats>()
        games.forEach { game ->
            game.results.playersBoxScore.forEach { (playerId: PlayerId, lineupPlayerBoxScore) ->
                val positions = mapPosition(lineupPlayerBoxScore.position)
                positions.forEach { position ->
                    val playerWithPosition = PlayerWithPosition(playerId, position)
                    if (!stats.containsKey(playerWithPosition)) {
                        stats[playerWithPosition] =
                            computeStatsForFirstTime(playerId, lineupPlayerBoxScore, game)
                    } else {
                        stats.computeIfPresent(
                            PlayerWithPosition(
                                playerId,
                                position
                            )
                        ) { _, playerStats ->
                            aggregateStats(playerStats, lineupPlayerBoxScore, game)
                        }
                    }
                }
            }
        }
        return stats
    }

    private fun mapPosition(position: Int): List<PlayerPosition> =
        when (position) {
            1 -> listOf(PlayerPosition.One, PlayerPosition.Guard, PlayerPosition.Wherever)
            2 -> listOf(PlayerPosition.Two, PlayerPosition.Guard, PlayerPosition.Wherever)
            3 -> listOf(PlayerPosition.Three, PlayerPosition.Forward, PlayerPosition.Wherever)
            4 -> listOf(PlayerPosition.Four, PlayerPosition.Forward, PlayerPosition.Wherever)
            5 -> listOf(PlayerPosition.Five, PlayerPosition.Center, PlayerPosition.Wherever)
            else -> throw IllegalArgumentException("Illegal position: $position")
        }

    private fun aggregateStats(
        playerStats: PlayerStats,
        lineupPlayerBoxScore: LineupPlayerBoxScore,
        game: Game,
    ) = playerStats.copy(
        played = playerStats.played + 1,
        wins = calculateWins(game, playerStats.token, playerStats.wins),
        losses = calculateLosses(game, playerStats.token, playerStats.losses),
        g = playerStats.g + calculateGoals(lineupPlayerBoxScore),
        fieldGoals = playerStats.fieldGoals + lineupPlayerBoxScore.fieldGoals,
        fieldGoalAttempts = playerStats.fieldGoalAttempts + lineupPlayerBoxScore.fieldGoalAttempts,
        threePoints = playerStats.threePoints + lineupPlayerBoxScore.threePoints,
        threePointAttempts = playerStats.threePointAttempts + lineupPlayerBoxScore.threePointAttempts,
        twoPoints = playerStats.twoPoints + lineupPlayerBoxScore.twoPoints,
        twoPointAttempts = playerStats.twoPointAttempts + lineupPlayerBoxScore.twoPointAttempts,
        freeThrows = playerStats.freeThrows + lineupPlayerBoxScore.freeThrows,
        freeThrowAttempts = playerStats.freeThrowAttempts + lineupPlayerBoxScore.freeThrowAttempts,
        defensiveRebounds = playerStats.defensiveRebounds + lineupPlayerBoxScore.defensiveRebounds,
        offensiveRebounds = playerStats.offensiveRebounds + lineupPlayerBoxScore.offensiveRebounds,
        rebounds = playerStats.rebounds + lineupPlayerBoxScore.totalRebounds,
        assists = playerStats.assists + lineupPlayerBoxScore.assists,
        steals = playerStats.steals + lineupPlayerBoxScore.steals,
        blocks = playerStats.blocks + lineupPlayerBoxScore.blocks,
        fouls = playerStats.fouls + lineupPlayerBoxScore.personalFouls,
        points = playerStats.points + lineupPlayerBoxScore.points,
        turnovers = playerStats.turnovers + lineupPlayerBoxScore.turnovers,
    )

    private fun computeStatsForFirstTime(
        playerId: PlayerId,
        lineupPlayerBoxScore: LineupPlayerBoxScore,
        game: Game,
    ) = PlayerStats(
        played = 1,
        wins = if (getGameResult(game, playerId) == GameResult.Win) 1 else 0,
        losses = if (getGameResult(game, playerId) == GameResult.Loss) 1 else 0,
        token = game.lineups[getPlayerTeamId(game, playerId)]!!.playersStats[playerId]!!.token,
        fullName = "",
        g = 0,
        fieldGoals = lineupPlayerBoxScore.fieldGoals,
        fieldGoalAttempts = lineupPlayerBoxScore.fieldGoalAttempts,
        threePoints = lineupPlayerBoxScore.threePoints,
        threePointAttempts = lineupPlayerBoxScore.threePointAttempts,
        twoPoints = lineupPlayerBoxScore.twoPoints,
        twoPointAttempts = lineupPlayerBoxScore.twoPointAttempts,
        freeThrows = lineupPlayerBoxScore.freeThrows,
        freeThrowAttempts = lineupPlayerBoxScore.freeThrowAttempts,
        defensiveRebounds = lineupPlayerBoxScore.defensiveRebounds,
        offensiveRebounds = lineupPlayerBoxScore.offensiveRebounds,
        rebounds = lineupPlayerBoxScore.totalRebounds,
        assists = lineupPlayerBoxScore.assists,
        steals = lineupPlayerBoxScore.steals,
        blocks = lineupPlayerBoxScore.blocks,
        fouls = lineupPlayerBoxScore.personalFouls,
        points = lineupPlayerBoxScore.points,
        turnovers = lineupPlayerBoxScore.turnovers,
    )

    private fun getGameResult(game: Game, playerId: PlayerId): GameResult {
//        println(playerName)
        val playerTeamId = getPlayerTeamId(game, playerId)
        val oppositeTeamId = game.lineups.filterKeys { it != playerTeamId }.values.first().teamId
        return when {
            game.results.scores[playerTeamId]!! > game.results.scores[oppositeTeamId]!! -> GameResult.Win
            game.results.scores[playerTeamId]!! < game.results.scores[oppositeTeamId]!! -> GameResult.Loss
            else -> GameResult.Tie
        }
    }

    private fun getPlayerTeamId(game: Game, playerId: PlayerId): TeamId =
        game.lineups.filterValues {
            it.playersStats.containsKey(playerId)
        }.values
            .first().teamId

    private fun calculateWins(game: Game, playerId: PlayerId, wins: Int): Int =
        if (getGameResult(game, playerId) == GameResult.Win) wins + 1
        else wins

    private fun calculateLosses(game: Game, playerId: PlayerId, losses: Int): Int =
        if (getGameResult(game, playerId) == GameResult.Loss) losses + 1
        else losses

    private fun calculateGoals(lineupPlayerBoxScore: LineupPlayerBoxScore): Int =
        lineupPlayerBoxScore.fieldGoals +
            lineupPlayerBoxScore.freeThrows +
            lineupPlayerBoxScore.twoPoints +
            lineupPlayerBoxScore.threePoints

    private enum class GameResult { Win, Loss, Tie }
}
