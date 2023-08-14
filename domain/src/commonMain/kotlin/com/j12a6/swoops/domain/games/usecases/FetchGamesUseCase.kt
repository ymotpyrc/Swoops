package com.j12a6.swoops.domain.games.usecases

import com.j12a6.swoops.domain.games.GameRepository
import kotlinx.coroutines.delay

class FetchGamesUseCase(
    private val getGameIdsToExcludeUseCase: GetGameIdsToExcludeUseCase,
    private val gameRepository: GameRepository,
) {

    suspend fun invoke() {
        print("Let's go!")
        val gameIdsToExclude = getGameIdsToExcludeUseCase.invoke()
        println("gameIdsToExclude count: ${gameIdsToExclude.count()}")
//        val lastFetchedGameId = gameRepository.getLastRequestedGameId() + 1
        val lastFetchedGameId = 362374
        val lastPlayedGameId = gameRepository.getLastPlayedGameId()
        println("lastFetchedGameId: ${lastFetchedGameId - 1}")
        println("lastPlayedGameId: $lastPlayedGameId")
        val gameIdsToRequest = (lastFetchedGameId..lastPlayedGameId).minus(gameIdsToExclude)
        gameIdsToRequest.forEach { gameId ->
            println("gameId: $gameId")
            when (val result = gameRepository.fetchGame(gameId)) {
                is FetchGameResult.Success -> {
                    gameRepository.saveGame(gameId, result.json)
                    gameRepository.updateLastRequestedGameId(gameId)
                }
                is FetchGameResult.Error -> gameRepository.storeError(gameId, result.error)
            }
            delay(1220)
        }
    }
}

sealed class FetchGameResult {
    data class Success(val json: String) : FetchGameResult()
    data class Error(val error: GameError) : FetchGameResult()
}

enum class GameError { RECOVERABLE, UNRECOVERABLE }
