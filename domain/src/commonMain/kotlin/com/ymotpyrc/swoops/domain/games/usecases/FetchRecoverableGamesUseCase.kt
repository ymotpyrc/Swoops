package com.ymotpyrc.swoops.domain.games.usecases

import com.ymotpyrc.swoops.domain.games.GameRepository
import kotlinx.coroutines.delay

class FetchRecoverableGamesUseCase(
    private val getGameIdsToExcludeUseCase: GetGameIdsToExcludeUseCase,
    private val gameRepository: GameRepository,
) {

    suspend fun invoke() {
        val gameIdsToExclude = getGameIdsToExcludeUseCase.invoke()
        val gameIds = gameRepository.getGameIdsToRetry()
        val gameIdsCount = gameIds.size
        println("gameIdsToExclude count: ${gameIdsToExclude.count()}")
        val gameIdsToRequest = gameIds.minus(gameIdsToExclude)
        gameIdsToRequest.forEachIndexed { index, gameId ->
            println("gameId: $gameId - ${index + 1} of $gameIdsCount")
            when (val result = gameRepository.fetchGame(gameId)) {
                is FetchGameResult.Success -> {
                    gameRepository.saveGame(gameId, result.json)
                    gameRepository.deleteGameIdFromToRetry(gameId)
                }
                is FetchGameResult.Error -> {
                    if (result.error == GameError.UNRECOVERABLE) {
                        gameRepository.deleteGameIdFromToRetry(gameId)
                    }
                    gameRepository.storeError(gameId, result.error)
                }
            }
            delay(1220)
        }
    }
}
