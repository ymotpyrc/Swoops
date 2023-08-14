package com.j12a6.swoops.data.games.mapper

import com.j12a6.swoops.data.games.model.GameDto
import com.j12a6.swoops.data.games.model.LineupBoxScoreDto
import com.j12a6.swoops.data.games.model.LineupDto
import com.j12a6.swoops.data.games.model.LineupPlayerBoxScoreDto
import com.j12a6.swoops.data.games.model.LineupPlayerStatsDto
import com.j12a6.swoops.domain.games.models.Game
import com.j12a6.swoops.domain.games.models.Lineup
import com.j12a6.swoops.domain.games.models.LineupBoxScore
import com.j12a6.swoops.domain.games.models.LineupPlayerBoxScore
import com.j12a6.swoops.domain.games.models.LineupPlayerStats
import com.j12a6.swoops.domain.games.models.Results
import com.j12a6.swoops.domain.games.models.TeamId
import kotlinx.datetime.toInstant

class GameMapper {

    fun map(gameDto: GameDto): Game =
        Game(
            id = gameDto.id,
            lineups = mapOf(
                gameDto.lineup1.team.id to mapLineup(gameDto.lineup1.team.id, gameDto.lineup1),
                gameDto.lineup2.team.id to mapLineup(gameDto.lineup2.team.id, gameDto.lineup2),
            ),
            results = mapResults(gameDto),
            playedAt = gameDto.contest.playedAt.toInstant(),
        )


    private fun mapLineup(teamId: TeamId, lineupDto: LineupDto): Lineup =
        Lineup(
            teamId = teamId,
            teamComposition = listOf(
                lineupDto.player1.id,
                lineupDto.player2.id,
                lineupDto.player3.id,
                lineupDto.player4.id,
                lineupDto.player5.id,
            ),
            playersStats = mapOf(
                lineupDto.player1.token to mapPlayerStats(lineupDto.player1),
                lineupDto.player2.token to mapPlayerStats(lineupDto.player2),
                lineupDto.player3.token to mapPlayerStats(lineupDto.player3),
                lineupDto.player4.token to mapPlayerStats(lineupDto.player4),
                lineupDto.player5.token to mapPlayerStats(lineupDto.player5),
            )
        )

    private fun mapPlayerStats(lineupPlayerStatsDto: LineupPlayerStatsDto): LineupPlayerStats =
        LineupPlayerStats(
            id = lineupPlayerStatsDto.id,
            team = lineupPlayerStatsDto.team,
            wins = lineupPlayerStatsDto.wins ?: 0,
            losses = lineupPlayerStatsDto.losses ?: 0,
            token = lineupPlayerStatsDto.token,
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
                gameDto.lineup1.team.id to gameDto.results.lineup1Score,
                gameDto.lineup2.team.id to gameDto.results.lineup2Score,
            ),
            boxScores = mapOf(
                gameDto.lineup1.team.id to mapBoxScore(gameDto.results.lineup1BoxScore),
                gameDto.lineup2.team.id to mapBoxScore(gameDto.results.lineup2BoxScore),
            ),
            playersBoxScore = mapOf(
                gameDto.lineup1.player1.token to mapPlayerBoxScore(
                    gameDto.results.lineup1player1BoxScore,
                    position = 1
                ),
                gameDto.lineup1.player2.token to mapPlayerBoxScore(
                    gameDto.results.lineup1player2BoxScore,
                    position = 2
                ),
                gameDto.lineup1.player3.token to mapPlayerBoxScore(
                    gameDto.results.lineup1player3BoxScore,
                    position = 3
                ),
                gameDto.lineup1.player4.token to mapPlayerBoxScore(
                    gameDto.results.lineup1player4BoxScore,
                    position = 4
                ),
                gameDto.lineup1.player5.token to mapPlayerBoxScore(
                    gameDto.results.lineup1player5BoxScore,
                    position = 5
                ),
                gameDto.lineup2.player1.token to mapPlayerBoxScore(
                    gameDto.results.lineup2player1BoxScore,
                    position = 1
                ),
                gameDto.lineup2.player2.token to mapPlayerBoxScore(
                    gameDto.results.lineup2player2BoxScore,
                    position = 2
                ),
                gameDto.lineup2.player3.token to mapPlayerBoxScore(
                    gameDto.results.lineup2player3BoxScore,
                    position = 3
                ),
                gameDto.lineup2.player4.token to mapPlayerBoxScore(
                    gameDto.results.lineup2player4BoxScore,
                    position = 4
                ),
                gameDto.lineup2.player5.token to mapPlayerBoxScore(
                    gameDto.results.lineup2player5BoxScore,
                    position = 5
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

    private fun mapPlayerBoxScore(playerBoxScoreDto: LineupPlayerBoxScoreDto, position: Int): LineupPlayerBoxScore =
        LineupPlayerBoxScore(
            id = playerBoxScoreDto.id,
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