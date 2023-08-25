package com.ymotpyrc.swoops.data.games.mappers

import com.ymotpyrc.swoops.data.games.models.GameDto
import com.ymotpyrc.swoops.data.games.models.LineupBoxScoreDto
import com.ymotpyrc.swoops.data.games.models.LineupDto
import com.ymotpyrc.swoops.data.games.models.LineupPlayerBoxScoreDto
import com.ymotpyrc.swoops.data.games.models.LineupPlayerStatsDto
import com.ymotpyrc.swoops.domain.games.models.Game
import com.ymotpyrc.swoops.domain.games.models.Lineup
import com.ymotpyrc.swoops.domain.games.models.LineupBoxScore
import com.ymotpyrc.swoops.domain.games.models.LineupPlayerBoxScore
import com.ymotpyrc.swoops.domain.games.models.LineupPlayerStats
import com.ymotpyrc.swoops.domain.games.models.PlayerId
import com.ymotpyrc.swoops.domain.games.models.Results
import com.ymotpyrc.swoops.domain.games.models.TeamId
import com.ymotpyrc.swoops.domain.players.usecases.GameId
import kotlinx.datetime.toInstant

class GameMapper {

    fun map(gameDto: GameDto): Game =
        Game(
            id = GameId(id = gameDto.id),
            lineups = mapOf(
                TeamId(id = gameDto.lineup1.team.id) to
                    mapLineup(
                        TeamId(id = gameDto.lineup1.team.id),
                        gameDto.lineup1,
                    ),
                TeamId(id = gameDto.lineup2.team.id) to
                    mapLineup(
                        TeamId(id = gameDto.lineup2.team.id),
                        gameDto.lineup2,
                    ),
            ),
            results = mapResults(gameDto),
            playedAt = gameDto.contest.playedAt.toInstant(),
        )

    private fun mapLineup(teamId: TeamId, lineupDto: LineupDto): Lineup =
        Lineup(
            teamId = teamId,
            teamComposition = listOf(
                PlayerId(id = lineupDto.player1.token),
                PlayerId(id = lineupDto.player2.token),
                PlayerId(id = lineupDto.player3.token),
                PlayerId(id = lineupDto.player4.token),
                PlayerId(id = lineupDto.player5.token),
            ),
            playersStats = mapOf(
                PlayerId(id = lineupDto.player1.token) to mapPlayerStats(lineupDto.player1, teamId),
                PlayerId(id = lineupDto.player2.token) to mapPlayerStats(lineupDto.player2, teamId),
                PlayerId(id = lineupDto.player3.token) to mapPlayerStats(lineupDto.player3, teamId),
                PlayerId(id = lineupDto.player4.token) to mapPlayerStats(lineupDto.player4, teamId),
                PlayerId(id = lineupDto.player5.token) to mapPlayerStats(lineupDto.player5, teamId),
            )
        )

    private fun mapPlayerStats(
        lineupPlayerStatsDto: LineupPlayerStatsDto,
        teamId: TeamId,
    ): LineupPlayerStats =
        LineupPlayerStats(
            playerId = lineupPlayerStatsDto.token,
            teamId = teamId,
            wins = lineupPlayerStatsDto.wins ?: 0,
            losses = lineupPlayerStatsDto.losses ?: 0,
            fullName = lineupPlayerStatsDto.fullName,
            age = lineupPlayerStatsDto.age,
            starRating = lineupPlayerStatsDto.starRating,
            g = lineupPlayerStatsDto.g,
            fieldGoals = lineupPlayerStatsDto.fieldGoals,
            fieldGoalAttempts = lineupPlayerStatsDto.fieldGoalAttempts,
            fieldGoalPercentage = lineupPlayerStatsDto.fieldGoalPercentage,
            threePoints = lineupPlayerStatsDto.threePoints,
            threePointAttempts = lineupPlayerStatsDto.threePointAttempts,
            threePointPercentage = lineupPlayerStatsDto.threePointPercentage,
            twoPoints = lineupPlayerStatsDto.twoPoints,
            twoPointAttempts = lineupPlayerStatsDto.twoPointAttempts,
            twoPointPercentage = lineupPlayerStatsDto.twoPointPercentage,
            freeThrows = lineupPlayerStatsDto.freeThrows,
            freeThrowAttempts = lineupPlayerStatsDto.freeThrowAttempts,
            freeThrowPercentage = lineupPlayerStatsDto.freeThrowPercentage,
            defensiveReboundsPerGame = lineupPlayerStatsDto.defensiveReboundsPerGame,
            offensiveReboundsPerGame = lineupPlayerStatsDto.offensiveReboundsPerGame,
            reboundsPerGame = lineupPlayerStatsDto.reboundsPerGame,
            assistsPerGame = lineupPlayerStatsDto.assistsPerGame,
            stealsPerGame = lineupPlayerStatsDto.stealsPerGame,
            blocksPerGame = lineupPlayerStatsDto.blocksPerGame,
            foulsPerGame = lineupPlayerStatsDto.foulsPerGame,
            pointsPerGame = lineupPlayerStatsDto.pointsPerGame,
            turnoversPerGame = lineupPlayerStatsDto.turnoversPerGame,
        )

    private fun mapResults(gameDto: GameDto): Results =
        Results(
            scores = mapOf(
                TeamId(id = gameDto.lineup1.team.id) to gameDto.results.lineup1Score,
                TeamId(id = gameDto.lineup2.team.id) to gameDto.results.lineup2Score,
            ),
            boxScores = mapOf(
                TeamId(id = gameDto.lineup1.team.id) to mapBoxScore(gameDto.results.lineup1BoxScore),
                TeamId(id = gameDto.lineup2.team.id) to mapBoxScore(gameDto.results.lineup2BoxScore),
            ),
            playersBoxScore = mapOf(
                PlayerId(id = gameDto.lineup1.player1.token) to mapPlayerBoxScore(
                    gameDto.results.lineup1player1BoxScore,
                    position = 1,
                ),
                PlayerId(id = gameDto.lineup1.player2.token) to mapPlayerBoxScore(
                    gameDto.results.lineup1player2BoxScore,
                    position = 2,
                ),
                PlayerId(id = gameDto.lineup1.player3.token) to mapPlayerBoxScore(
                    gameDto.results.lineup1player3BoxScore,
                    position = 3,
                ),
                PlayerId(id = gameDto.lineup1.player4.token) to mapPlayerBoxScore(
                    gameDto.results.lineup1player4BoxScore,
                    position = 4,
                ),
                PlayerId(id = gameDto.lineup1.player5.token) to mapPlayerBoxScore(
                    gameDto.results.lineup1player5BoxScore,
                    position = 5,
                ),
                PlayerId(id = gameDto.lineup2.player1.token) to mapPlayerBoxScore(
                    gameDto.results.lineup2player1BoxScore,
                    position = 1,
                ),
                PlayerId(id = gameDto.lineup2.player2.token) to mapPlayerBoxScore(
                    gameDto.results.lineup2player2BoxScore,
                    position = 2,
                ),
                PlayerId(id = gameDto.lineup2.player3.token) to mapPlayerBoxScore(
                    gameDto.results.lineup2player3BoxScore,
                    position = 3,
                ),
                PlayerId(id = gameDto.lineup2.player4.token) to mapPlayerBoxScore(
                    gameDto.results.lineup2player4BoxScore,
                    position = 4,
                ),
                PlayerId(id = gameDto.lineup2.player5.token) to mapPlayerBoxScore(
                    gameDto.results.lineup2player5BoxScore,
                    position = 5,
                ),
            ),
        )

    private fun mapBoxScore(boxScoreDto: LineupBoxScoreDto): LineupBoxScore =
        LineupBoxScore(
            id = boxScoreDto.id,
            assists = boxScoreDto.assists,
            blocks = boxScoreDto.blocks,
            defensiveRebounds = boxScoreDto.defensiveRebounds,
            fieldGoals = boxScoreDto.fieldGoals,
            fieldGoalPercentage = boxScoreDto.fieldGoalPercentage,
            fieldGoalAttempts = boxScoreDto.fieldGoalAttempts,
            freeThrows = boxScoreDto.freeThrows,
            freeThrowPercentage = boxScoreDto.freeThrowPercentage,
            freeThrowAttempts = boxScoreDto.freeThrowAttempts,
            offensiveRebounds = boxScoreDto.offensiveRebounds,
            personalFouls = boxScoreDto.personalFouls,
            points = boxScoreDto.points,
            steals = boxScoreDto.steals,
            threePoints = boxScoreDto.threePoints,
            threePointPercentage = boxScoreDto.threePointPercentage,
            threePointAttempts = boxScoreDto.threePointAttempts,
            turnovers = boxScoreDto.turnovers,
            totalRebounds = boxScoreDto.totalRebounds,
            twoPoints = boxScoreDto.twoPoints,
            twoPointPercentage = boxScoreDto.twoPointPercentage,
            twoPointAttempts = boxScoreDto.twoPointAttempts,
        )

    private fun mapPlayerBoxScore(
        playerBoxScoreDto: LineupPlayerBoxScoreDto,
        position: Int,
    ): LineupPlayerBoxScore =
        LineupPlayerBoxScore(
            position = position,
            assists = playerBoxScoreDto.assists,
            blocks = playerBoxScoreDto.blocks,
            defensiveRebounds = playerBoxScoreDto.defensiveRebounds,
            fieldGoals = playerBoxScoreDto.fieldGoals,
            fieldGoalAttempts = playerBoxScoreDto.fieldGoalAttempts,
            fieldGoalPercentage = playerBoxScoreDto.fieldGoalPercentage,
            freeThrows = playerBoxScoreDto.freeThrows,
            freeThrowAttempts = playerBoxScoreDto.freeThrowAttempts,
            freeThrowPercentage = playerBoxScoreDto.freeThrowPercentage,
            offensiveRebounds = playerBoxScoreDto.offensiveRebounds,
            personalFouls = playerBoxScoreDto.personalFouls,
            points = playerBoxScoreDto.points,
            steals = playerBoxScoreDto.steals,
            threePoints = playerBoxScoreDto.threePoints,
            threePointAttempts = playerBoxScoreDto.threePointAttempts,
            threePointPercentage = playerBoxScoreDto.threePointPercentage,
            turnovers = playerBoxScoreDto.turnovers,
            totalRebounds = playerBoxScoreDto.totalRebounds,
            twoPoints = playerBoxScoreDto.twoPoints,
            twoPointAttempts = playerBoxScoreDto.twoPointAttempts,
            twoPointPercentage = playerBoxScoreDto.twoPointPercentage,
        )
}