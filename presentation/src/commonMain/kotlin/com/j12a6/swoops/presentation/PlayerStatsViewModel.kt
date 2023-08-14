package com.j12a6.swoops.presentation

import com.j12a6.swoops.domain.games.models.PlayerId
import com.j12a6.swoops.players.models.PlayerWithPosition
import com.j12a6.swoops.domain.games.usecases.FilterGamesOnDateUseCase
import com.j12a6.swoops.domain.games.usecases.FilterGamesOnFreeAgentsUseCase
import com.j12a6.swoops.domain.games.usecases.GetGamesUseCase
import com.j12a6.swoops.domain.players.usecases.GetPlayersStatsUseCase
import kotlinx.datetime.Instant

class PlayerStatsViewModel(
    private val getGamesUseCase: GetGamesUseCase,
    private val filterGamesOnFreeAgentsUseCase: FilterGamesOnFreeAgentsUseCase,
    private val filterGamesOnDateUseCase: FilterGamesOnDateUseCase,
    private val getPlayersStatsUseCase: GetPlayersStatsUseCase,
    private val playerStatsUiMapper: PlayerStatsUiMapper,
) {

    suspend fun start(playerIds: List<PlayerId>): Map<PlayerWithPosition, PlayerStatsUi> {
        val date = "2023-03-03T23:30:00.000000Z"
        val games = getGamesUseCase.invoke()
            .let { filterGamesOnFreeAgentsUseCase.execute(it)}
            .let {filterGamesOnDateUseCase.invoke(it, Instant.parse(date), FilterGamesOnDateUseCase.Moment.After) }
            .toList()
        val players = getPlayersStatsUseCase.invoke(games)
        val filteredPlayers = players.filterKeys { playerIds.contains(it.id) }
        return filteredPlayers.mapValues { playerStatsUiMapper.map(it.value) }
    }
}