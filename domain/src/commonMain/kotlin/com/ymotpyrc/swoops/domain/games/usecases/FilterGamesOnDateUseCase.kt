package com.ymotpyrc.swoops.domain.games.usecases

import com.ymotpyrc.swoops.domain.games.models.Game
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

