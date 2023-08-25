package com.ymotpyrc.swoops.domain.games.usecases

import com.ymotpyrc.swoops.domain.games.models.Game
import com.ymotpyrc.swoops.domain.games.GameRepository

class GetGamesUseCase(
    private val gameRepository: GameRepository,
) {

    fun invoke(): Sequence<Game> = gameRepository.getGames()
}