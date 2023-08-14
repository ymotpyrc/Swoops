package com.j12a6.swoops.domain.games.usecases

import com.j12a6.swoops.domain.games.models.Game
import com.j12a6.swoops.domain.games.GameRepository

class GetGamesUseCase(
    private val gameRepository: GameRepository,
) {

    fun invoke(): Sequence<Game> = gameRepository.getGames()
}