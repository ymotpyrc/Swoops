package com.ymotpyrc.swoops.domain.games.usecases

import com.ymotpyrc.swoops.domain.games.models.Game
import com.ymotpyrc.swoops.domain.games.models.TeamId

class FilterGamesOnTeamUseCase {

    fun invoke(games: Sequence<Game>, teamId: TeamId, teamFilter: TeamFilter): Sequence<Game> =
        when (teamFilter) {
            TeamFilter.Present -> games.filter { game -> game.lineups.keys.contains(teamId) }
            TeamFilter.Absent -> games.filterNot { game -> game.lineups.keys.contains(teamId) }
        }
}

enum class TeamFilter { Present, Absent }