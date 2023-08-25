package com.ymotpyrc.swoops.domain.games.usecases

import com.ymotpyrc.swoops.domain.games.models.Game
import com.ymotpyrc.swoops.domain.games.models.Lineup
import com.ymotpyrc.swoops.domain.games.models.PlayerId
import com.ymotpyrc.swoops.domain.games.models.TeamId

class FilterGamesOnPlayersUseCase {

    fun invoke(games: Sequence<Game>, playerIds: List<PlayerId>): Sequence<Game> =
        games.filter { game ->
            playerIds.any { playerId ->
                isPlayerInLineup(game.lineups, playerId)
            }
        }

    private fun isPlayerInLineup(lineups: Map<TeamId, Lineup>, playerId: PlayerId) =
        lineups.any { lineup ->
            lineup.value.playersStats.any { it.value.playerId == playerId.id }
        }
}
