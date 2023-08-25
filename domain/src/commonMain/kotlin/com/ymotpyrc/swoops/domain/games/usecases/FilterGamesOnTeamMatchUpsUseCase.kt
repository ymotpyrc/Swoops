package com.ymotpyrc.swoops.domain.games.usecases

import com.ymotpyrc.swoops.domain.games.models.Game
import com.ymotpyrc.swoops.domain.games.models.TeamId

class FilterGamesOnTeamMatchUpsUseCase {

    fun invoke(games: Sequence<Game>, teams: Set<TeamId>): Sequence<Game> =
        games.filter { game ->
            game.lineups.keys.toSet() == teams
        }
}
