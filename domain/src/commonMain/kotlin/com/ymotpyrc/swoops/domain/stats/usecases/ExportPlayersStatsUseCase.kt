package com.ymotpyrc.swoops.domain.stats.usecases

import com.ymotpyrc.swoops.domain.games.usecases.FilterGamesOnFreeAgentsUseCase
import com.ymotpyrc.swoops.domain.games.usecases.FilterGamesOnTeamUseCase
import com.ymotpyrc.swoops.domain.games.usecases.GetGamesUseCase
import com.ymotpyrc.swoops.domain.stats.PlayersStatsRepository

class ExportPlayersStatsUseCase(
    private val computePlayersStatsUseCase: ComputePlayersStatsUseCase,
    private val gamesUseCase: GetGamesUseCase,
    private val filterGamesOnFreeAgentsUseCase: FilterGamesOnFreeAgentsUseCase,
    private val filterGamesOnTeamUseCase: FilterGamesOnTeamUseCase,
    private val playersStatsRepository: PlayersStatsRepository,
) {

    fun invoke() {
        val games = gamesUseCase.invoke()
            .let { filterGamesOnFreeAgentsUseCase.invoke(it) }
//            .let { filterGamesOnTeamUseCase.invoke(it, TeamId(id = 435), teamFilter = TeamFilter.Absent) }
            .toList()
        val stats = computePlayersStatsUseCase.invoke(games)
        playersStatsRepository.savePlayerStats(stats)
    }
}