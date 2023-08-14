package com.j12a6.swoops.domain.games.usecases

import com.j12a6.swoops.domain.games.models.Game
import com.j12a6.swoops.domain.games.models.TeamId

class FilterGamesOnTeamMatchUpsUseCase {

    fun invoke(games: Sequence<Game>, teams: Set<TeamId>): Sequence<Game> =
        games.filter { game ->
            game.lineups.keys.toSet() == teams
        }
}
