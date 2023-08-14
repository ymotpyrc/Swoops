package com.j12a6.swoops.domain.games.usecases

import com.j12a6.swoops.domain.games.GameRepository
import kotlinx.coroutines.delay

class FetchRecoverableGamesUseCase(
    private val getGameIdsToExcludeUseCase: GetGameIdsToExcludeUseCase,
    private val gameRepository: GameRepository,
) {

    suspend fun invoke() {
        val gameIdsToExclude = getGameIdsToExcludeUseCase.invoke()
        val gameIds = gameRepository.getGameIdsToRetry()
        println("gameIdsToExclude count: ${gameIdsToExclude.count()}")
        val gameIdsToRequest = gameIds.minus(gameIdsToExclude)
        gameIdsToRequest.forEach { gameId ->
            println("gameId: $gameId")
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
