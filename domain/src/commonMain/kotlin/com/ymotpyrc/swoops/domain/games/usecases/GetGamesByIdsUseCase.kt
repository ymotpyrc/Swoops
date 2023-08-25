package com.ymotpyrc.swoops.domain.games.usecases

import com.ymotpyrc.swoops.domain.games.models.Game
import com.ymotpyrc.swoops.domain.games.GameRepository
import com.ymotpyrc.swoops.domain.players.usecases.GameId

class GetGamesByIdsUseCase(
    private val gameRepository: GameRepository,
) {

    fun invoke(gameIds: Sequence<GameId>): Sequence<Game> = gameRepository.getGamesByIds(gameIds)
}