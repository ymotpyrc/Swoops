package com.j12a6.swoops.domain.games.usecases

import com.j12a6.swoops.domain.games.models.Game
import kotlinx.datetime.Instant

class FilterGamesOnDateUseCase {

    suspend fun invoke(games: Sequence<Game>, date: Instant, moment: Moment): Sequence<Game> =
        games.filter { game ->
            when (moment) {
                Moment.Before -> game.playedAt <= date
                Moment.After -> game.playedAt >= date
            }
        }

    enum class Moment { Before, After }
}

