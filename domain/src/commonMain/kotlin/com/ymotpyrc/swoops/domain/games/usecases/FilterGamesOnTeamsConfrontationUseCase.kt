package com.ymotpyrc.swoops.domain.games.usecases

import com.ymotpyrc.swoops.domain.games.models.Game
import com.ymotpyrc.swoops.domain.games.models.TeamId

class FilterGamesOnTeamsConfrontationUseCase {

    fun invoke(games: Sequence<Game>, teamId: TeamId, opponentTeamId: TeamId): Sequence<Game> =
        games.filter { game ->
            (game.lineups.keys.first() == teamId && game.lineups.keys.last() == opponentTeamId) ||
                (game.lineups.keys.first() == opponentTeamId && game.lineups.keys.last() == teamId)
        }
}
