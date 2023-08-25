package com.ymotpyrc.swoops.presentation

import com.ymotpyrc.swoops.domain.games.models.PlayerId
import com.ymotpyrc.swoops.domain.stats.usecases.GetPlayersStatsUseCase

class ComparePlayersStatsViewModel(
    private val getPlayersStatsUseCase: GetPlayersStatsUseCase,
    private val playerStatsUiMapper: PlayerStatsUiMapper,
) {

    fun start(playerId: PlayerId, otherPlayerIds: List<PlayerId>): List<PlayerStatsUi> {
        val playersStats = getPlayersStatsUseCase.invoke()
        val filteredPlayersStats = playersStats.filterKeys { it.id == playerId } +
            playersStats.filterKeys { otherPlayerIds.contains(it.id) }
        return filteredPlayersStats.map { (playerWithPosition, stats) ->
            playerStatsUiMapper.map(playerWithPosition.id, stats)
        }
    }
}