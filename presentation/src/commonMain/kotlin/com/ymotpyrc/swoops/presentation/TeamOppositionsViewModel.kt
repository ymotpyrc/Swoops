package com.ymotpyrc.swoops.presentation

import com.ymotpyrc.swoops.domain.games.models.TeamId
import com.ymotpyrc.swoops.domain.games.usecases.FilterGamesOnTeamUseCase
import com.ymotpyrc.swoops.domain.games.usecases.GetGamesUseCase
import com.ymotpyrc.swoops.domain.games.usecases.TeamFilter

class TeamOppositionsViewModel(
    private val getGamesUseCase: GetGamesUseCase,
    private val filterGamesOnTeamUseCase: FilterGamesOnTeamUseCase,
) {

    fun start(teamId: TeamId): List<TeamOpposition> {
        val games = getGamesUseCase.invoke().let {
            filterGamesOnTeamUseCase.invoke(it, teamId, teamFilter = TeamFilter.Present)
        }.toList()
        val opponents = games.map { game ->
            game.lineups.filterKeys { it != teamId }.keys.first()
        }
        val oppositions = opponents.associateWith { team2Id ->
            val gamesSequence = sequenceOf(*games.toTypedArray())
            val gamesAgainstThisTeam =
                gamesSequence.let { filterGamesOnTeamUseCase.invoke(it, team2Id, teamFilter = TeamFilter.Present) }
            TeamOpposition(
                opponentTeamId = team2Id,
                played = gamesAgainstThisTeam.count(),
                wins = gamesAgainstThisTeam.count { it.results.scores[teamId]!! > it.results.scores[team2Id]!! },
                losses = gamesAgainstThisTeam.count { it.results.scores[teamId]!! < it.results.scores[team2Id]!! },
                ties = gamesAgainstThisTeam.count { it.results.scores[teamId]!! == it.results.scores[team2Id]!! },
            )
        }
        return oppositions.values.sortedBy { it.wins.toFloat() / it.played }
    }
}
