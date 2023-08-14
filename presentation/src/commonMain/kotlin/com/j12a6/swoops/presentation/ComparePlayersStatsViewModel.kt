package com.j12a6.swoops.presentation

import com.j12a6.swoops.domain.games.models.PlayerId
import com.j12a6.swoops.domain.games.usecases.GetGamesUseCase
import com.j12a6.swoops.domain.players.usecases.GetPlayersStatsUseCase

class ComparePlayersStatsViewModel(
    private val getGamesUseCase: GetGamesUseCase,
    private val getPlayersStatsUseCase: GetPlayersStatsUseCase,
    private val playerStatsUiMapper: PlayerStatsUiMapper,
) {

    fun start(playerId: PlayerId, otherPlayerIds: List<PlayerId>): List<PlayerStatsUi> {
        val games = getGamesUseCase.invoke().toList()
        val players = getPlayersStatsUseCase.invoke(games)
        val filteredPlayers = players.filterKeys { it.id == playerId } +
            players.filterKeys { otherPlayerIds.contains(it.id) }
        return filteredPlayers.values.map(playerStatsUiMapper::map)
    }
}