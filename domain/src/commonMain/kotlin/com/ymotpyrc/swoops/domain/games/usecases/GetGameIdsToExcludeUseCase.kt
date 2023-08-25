package com.ymotpyrc.swoops.domain.games.usecases

import com.ymotpyrc.swoops.domain.games.GameRepository
import com.ymotpyrc.swoops.domain.players.usecases.GameId

class GetGameIdsToExcludeUseCase(
    private val gameRepository: GameRepository,
) {

    fun invoke(): Set<GameId> = gameRepository.getGameIdsToExclude()
}
