package com.ymotpyrc.swoops.domain.games.usecases

import com.ymotpyrc.swoops.domain.games.models.Game
import com.ymotpyrc.swoops.domain.games.models.Lineup
import com.ymotpyrc.swoops.domain.games.models.TeamId

class FilterGamesOnFreeAgentsUseCase {

    fun invoke(games: Sequence<Game>): Sequence<Game> =
        games.filter { game -> !isFreeAgentPlaying(game.lineups) }

    private fun isFreeAgentPlaying(lineups: Map<TeamId, Lineup>): Boolean =
        lineups.any { lineup ->
            lineup.value.playersStats.any { it.value.playerId < 0 }
        }
}
