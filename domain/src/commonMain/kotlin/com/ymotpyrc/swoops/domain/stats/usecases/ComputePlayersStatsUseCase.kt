package com.ymotpyrc.swoops.domain.stats.usecases

import com.ymotpyrc.swoops.domain.games.models.Game
import com.ymotpyrc.swoops.domain.games.models.LineupPlayerBoxScore
import com.ymotpyrc.swoops.domain.games.models.PlayerId
import com.ymotpyrc.swoops.domain.games.models.TeamId
import com.ymotpyrc.swoops.domain.stats.models.Assists
import com.ymotpyrc.swoops.domain.stats.models.Blocks
import com.ymotpyrc.swoops.domain.stats.models.DefensiveRebounds
import com.ymotpyrc.swoops.domain.stats.models.FieldGoalAttempts
import com.ymotpyrc.swoops.domain.stats.models.FieldGoals
import com.ymotpyrc.swoops.domain.stats.models.Fouls
import com.ymotpyrc.swoops.domain.stats.models.FreeThrowAttempts
import com.ymotpyrc.swoops.domain.stats.models.FreeThrows
import com.ymotpyrc.swoops.domain.stats.models.Goals
import com.ymotpyrc.swoops.domain.stats.models.Losses
import com.ymotpyrc.swoops.domain.stats.models.OffensiveRebounds
import com.ymotpyrc.swoops.domain.stats.models.Played
import com.ymotpyrc.swoops.domain.stats.models.PlayerPosition
import com.ymotpyrc.swoops.domain.stats.models.PlayerStats
import com.ymotpyrc.swoops.domain.stats.models.PlayerWithPosition
import com.ymotpyrc.swoops.domain.stats.models.Points
import com.ymotpyrc.swoops.domain.stats.models.Rebounds
import com.ymotpyrc.swoops.domain.stats.models.Season
import com.ymotpyrc.swoops.domain.stats.models.Steals
import com.ymotpyrc.swoops.domain.stats.models.ThreePointAttempts
import com.ymotpyrc.swoops.domain.stats.models.ThreePoints
import com.ymotpyrc.swoops.domain.stats.models.Turnovers
import com.ymotpyrc.swoops.domain.stats.models.TwoPointAttempts
import com.ymotpyrc.swoops.domain.stats.models.TwoPoints
import com.ymotpyrc.swoops.domain.stats.models.Wins
import com.ymotpyrc.swoops.domain.stats.models.plus
import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant

class ComputePlayersStatsUseCase {

    fun invoke(games: List<Game>): Map<PlayerWithPosition, PlayerStats> {
        val stats = mutableMapOf<PlayerWithPosition, PlayerStats>()
        games
            .forEach { game ->
                game.results.playersBoxScore.forEach { (playerId: PlayerId, lineupPlayerBoxScore) ->
                    val positions = mapPosition(lineupPlayerBoxScore.position)
                    val seasons = listOf(Season.All, mapSeason(game.playedAt))
                    seasons.forEach { season ->
                        positions.forEach { position ->
                            val playerWithPosition = PlayerWithPosition(playerId, position, season)
                            if (!stats.containsKey(playerWithPosition)) {
                                stats[playerWithPosition] =
                                    computeStatsForFirstTime(playerId, lineupPlayerBoxScore, game)
                            } else {
                                stats.computeIfPresent(playerWithPosition) { _, playerStats ->
                                    aggregateStats(playerId, playerStats, lineupPlayerBoxScore, game)
                                }
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

    private fun mapSeason(playedAt: Instant): Season.Number {
        val startOfSeason1 = "2023-03-03T23:30:00.000000Z".toInstant() // 1st SIM update
        val startOfSeason2 = "2023-08-15T00:00:00.000000Z".toInstant() // 2nd SIM update?
        return when {
            playedAt < startOfSeason1 -> Season.Number(nb = 0)
            playedAt < startOfSeason2 -> Season.Number(nb = 1)
            else -> Season.Number(nb = 2)
        }
    }

    private fun aggregateStats(
        playerId: PlayerId,
        playerStats: PlayerStats,
        lineupPlayerBoxScore: LineupPlayerBoxScore,
        game: Game,
    ) = playerStats.copy(
        played = playerStats.played + 1,
        wins = calculateWins(game, playerId, playerStats.wins),
        losses = calculateLosses(game, playerId, playerStats.losses),
        goals = playerStats.goals + calculateGoals(lineupPlayerBoxScore),
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
        played = Played(1),
        wins = Wins(if (getGameResult(game, playerId) == GameResult.Win) 1 else 0),
        losses = Losses(if (getGameResult(game, playerId) == GameResult.Loss) 1 else 0),
        goals = Goals(0),
        fieldGoals = FieldGoals(lineupPlayerBoxScore.fieldGoals),
        fieldGoalAttempts = FieldGoalAttempts(lineupPlayerBoxScore.fieldGoalAttempts),
        threePoints = ThreePoints(lineupPlayerBoxScore.threePoints),
        threePointAttempts = ThreePointAttempts(lineupPlayerBoxScore.threePointAttempts),
        twoPoints = TwoPoints(lineupPlayerBoxScore.twoPoints),
        twoPointAttempts = TwoPointAttempts(lineupPlayerBoxScore.twoPointAttempts),
        freeThrows = FreeThrows(lineupPlayerBoxScore.freeThrows),
        freeThrowAttempts = FreeThrowAttempts(lineupPlayerBoxScore.freeThrowAttempts),
        defensiveRebounds = DefensiveRebounds(lineupPlayerBoxScore.defensiveRebounds),
        offensiveRebounds = OffensiveRebounds(lineupPlayerBoxScore.offensiveRebounds),
        rebounds = Rebounds(lineupPlayerBoxScore.totalRebounds),
        assists = Assists(lineupPlayerBoxScore.assists),
        steals = Steals(lineupPlayerBoxScore.steals),
        blocks = Blocks(lineupPlayerBoxScore.blocks),
        fouls = Fouls(lineupPlayerBoxScore.personalFouls),
        points = Points(lineupPlayerBoxScore.points),
        turnovers = Turnovers(lineupPlayerBoxScore.turnovers),
    )

    private fun getGameResult(game: Game, playerId: PlayerId): GameResult {
        val playerTeamId = getPlayerTeamId(game, playerId)
        val oppositeTeamId = game.lineups.filterKeys { it != playerTeamId }.values.first().teamId
        return when {
            game.results.scores[playerTeamId]!! > game.results.scores[oppositeTeamId]!! -> GameResult.Win
            game.results.scores[playerTeamId]!! < game.results.scores[oppositeTeamId]!! -> GameResult.Loss
            else -> GameResult.Tie
        }
    }

    private fun getPlayerTeamId(game: Game, playerId: PlayerId): TeamId =
        game.lineups
            .filterValues { it.playersStats.containsKey(playerId) }
            .values.first()
            .teamId

    private fun calculateWins(game: Game, playerId: PlayerId, wins: Wins): Wins =
        if (getGameResult(game, playerId) == GameResult.Win) wins + 1
        else wins

    private fun calculateLosses(game: Game, playerId: PlayerId, losses: Losses): Losses =
        if (getGameResult(game, playerId) == GameResult.Loss) losses + 1
        else losses

    private fun calculateGoals(lineupPlayerBoxScore: LineupPlayerBoxScore): Int =
        lineupPlayerBoxScore.fieldGoals +
            lineupPlayerBoxScore.freeThrows +
            lineupPlayerBoxScore.twoPoints +
            lineupPlayerBoxScore.threePoints

    private enum class GameResult { Win, Loss, Tie }
}
