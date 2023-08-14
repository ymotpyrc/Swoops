package com.j12a6.swoops.domain.games.usecases

import com.j12a6.swoops.domain.games.models.Game
import com.j12a6.swoops.domain.games.models.TeamId

class FilterGamesOnTeamUseCase {

    fun invoke(games: Sequence<Game>, teamId: TeamId): Sequence<Game> =
        games.filter { game ->
            game.lineups.keys.contains(teamId)
        }
}
