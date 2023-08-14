package com.j12a6.swoops.domain.games.usecases

import com.j12a6.swoops.domain.games.models.Game
import com.j12a6.swoops.domain.games.GameRepository
import com.j12a6.swoops.domain.players.usecases.GameId

class GetGamesByIdsUseCase(
    private val gameRepository: GameRepository,
) {

    fun invoke(gameIds: Sequence<GameId>): Sequence<Game> = gameRepository.getGamesByIds(gameIds)
}