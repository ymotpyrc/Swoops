package com.j12a6.swoops.domain.games.usecases

import com.j12a6.swoops.domain.games.GameRepository
import com.j12a6.swoops.domain.players.usecases.GameId

class GetGameIdsToExcludeUseCase(
    private val gameRepository: GameRepository,
) {

    fun invoke(): Set<GameId> = gameRepository.getGameIdsToExclude()
}
