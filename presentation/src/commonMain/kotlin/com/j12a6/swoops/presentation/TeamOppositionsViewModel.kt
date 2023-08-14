package com.j12a6.swoops.presentation

import com.j12a6.swoops.domain.games.usecases.FilterGamesOnTeamUseCase
import com.j12a6.swoops.domain.games.usecases.GetGameIdsToExcludeUseCase
import com.j12a6.swoops.domain.games.usecases.GetGamesUseCase

class TeamOppositionsViewModel(
    private val getGamesUseCase: GetGamesUseCase,
    private val getGameIdsToExcludeUseCase: GetGameIdsToExcludeUseCase,
    private val filterGamesOnTeamUseCase: FilterGamesOnTeamUseCase,
) {

//    suspend fun start(teamId: TeamId): List<TeamOpposition> {
//        val gameIdsToExclude = getGameIdsToExcludeUseCase.invoke()
//        val games = getGamesUseCase.invoke(gameIdsToExclude).let {
//            filterGamesOnTeamUseCase.invoke(it, teamId)
//        }.toList()
//        val oppositions = games.map { game ->
//            acc.
//            games.count()
//            games.count { it.results.scores[95]!! > it.results.scores[22]!! }
//            games.count { it.results.scores[95]!! < it.results.scores[22]!! }
//            games.count { it.results.scores[95]!! == it.results.scores[22]!! }
//            games.map { it.id }.sortedDescending()
//        }
//        val filteredPlayers = players.filterKeys { it == playerId } +
//            players.filterKeys { otherPlayerIds.contains(it) }
//        return filteredPlayers.values.map(playerStatsUiMapper::map)
//    }
}