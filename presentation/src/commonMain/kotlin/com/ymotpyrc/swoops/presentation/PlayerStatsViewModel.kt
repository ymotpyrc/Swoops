package com.ymotpyrc.swoops.presentation

import com.ymotpyrc.swoops.domain.games.models.PlayerId
import com.ymotpyrc.swoops.domain.games.usecases.FilterGamesOnDateUseCase
import com.ymotpyrc.swoops.domain.games.usecases.FilterGamesOnFreeAgentsUseCase
import com.ymotpyrc.swoops.domain.games.usecases.FilterGamesOnPlayersUseCase
import com.ymotpyrc.swoops.domain.games.usecases.GetGamesByIdsUseCase
import com.ymotpyrc.swoops.domain.games.usecases.GetGamesUseCase
import com.ymotpyrc.swoops.domain.stats.models.Percentile
import com.ymotpyrc.swoops.domain.stats.models.PlayerWithPosition
import com.ymotpyrc.swoops.domain.stats.usecases.GetPlayersStatsPercentilesUseCase
import com.ymotpyrc.swoops.domain.stats.usecases.GetPlayersStatsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class PlayerStatsViewModel(
    private val getGamesUseCase: GetGamesUseCase,
    private val getGamesByIdsUseCase: GetGamesByIdsUseCase,
    private val filterGamesOnPlayers: FilterGamesOnPlayersUseCase,
    private val filterGamesOnFreeAgentsUseCase: FilterGamesOnFreeAgentsUseCase,
    private val filterGamesOnDateUseCase: FilterGamesOnDateUseCase,
    private val getPlayersStatsUseCase: GetPlayersStatsUseCase,
    private val getPlayersStatsPercentilesUseCase: GetPlayersStatsPercentilesUseCase,
    private val playerStatsUiMapper: PlayerStatsUiMapper,
    coroutineScope: CoroutineScope,
) {

    private val _state: MutableStateFlow<PlayerState> = MutableStateFlow(PlayerState.Loading)

    val state: StateFlow<PlayerState> = _state.stateIn(
        scope = coroutineScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 3000L),
        PlayerState.Loading,
    )

    suspend fun search(playerId: PlayerId) {
//        val date = "2023-03-03T23:30:00.000000Z" // 1st SIM update
//        val date = "2023-08-15T00:00:00.000000Z" // 2nd SIM update?
//            getGamesUseCase.invoke()
//                .let { filterGamesOnPlayers.invoke(it, listOf(playerId)) }
//                .let {
//                    filterGamesOnDateUseCase.invoke(
//                        it,
//                        Instant.parse(date),
//                        FilterGamesOnDateUseCase.Moment.After,
//                    )
//                }.toList()
        val playersStats = getPlayersStatsUseCase.invoke()
        val filteredPlayersStats = playersStats.filterKeys { playerId == it.id }
        val playersStatsUi = filteredPlayersStats.mapValues { (playerWithPosition, stats) ->
            playerStatsUiMapper.map(playerWithPosition.id, stats)
        }
        val playersStatsPercentiles = getPlayersStatsPercentilesUseCase.invoke()
        val playersStatsPercentilesUi = playersStatsPercentiles.map { entry ->
            entry.key to entry.value.mapValues { (_, stats) ->
                playerStatsUiMapper.map(entry.key.id, stats)
            }
        }.toMap()

//        playersStatsUi.forEach { println("position: ${it.key}\n ${it.value}") }
        _state.emit(
            PlayerState.Data(
                playerId = playerId,
                playersStatsUi = playersStatsUi,
                playerStatsPercentiles = playersStatsPercentilesUi,
            )
        )
    }
}

sealed interface PlayerState {

    object Loading : PlayerState
    data class Data(
        val playerId: PlayerId,
        val playersStatsUi: Map<PlayerWithPosition, PlayerStatsUi>,
        val playerStatsPercentiles: Map<PlayerWithPosition, Map<Percentile, PlayerStatsUi>>,
    ) : PlayerState
}
