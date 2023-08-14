package com.j12a6.swoops.domain.games.usecases

import com.j12a6.swoops.domain.games.models.Game
import com.j12a6.swoops.domain.games.models.Lineup
import com.j12a6.swoops.domain.games.models.TeamId

class FilterGamesOnFreeAgentsUseCase {

    fun execute(games: Sequence<Game>): Sequence<Game> =
        games.filter { game -> !isFreeAgentPlaying(game.lineups) }

    private fun isFreeAgentPlaying(lineups: Map<TeamId, Lineup>): Boolean =
        lineups.any { lineup ->
            lineup.value.playersStats.any { it.value.fullName.startsWith("FREEAGENT-") }
        }
}
