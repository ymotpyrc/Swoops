package com.ymotpyrc.swoops.domain.games.usecases

import com.ymotpyrc.swoops.domain.games.GameRepository
import com.ymotpyrc.swoops.domain.players.usecases.GameId
import kotlinx.coroutines.delay

class FetchGamesUseCase(
    private val getGameIdsToExcludeUseCase: GetGameIdsToExcludeUseCase,
    private val gameRepository: GameRepository,
) {

    suspend fun invoke() {
        println("Let's go!")
        val gameIdsToExclude = getGameIdsToExcludeUseCase.invoke()
            .map { gameId -> gameId.id }.toSet()
        println("gameIdsToExclude count: ${gameIdsToExclude.count()}")
        val lastFetchedGameId = gameRepository.getLastRequestedGameId()
        val lastPlayedGameId = gameRepository.getLastPlayedGameId()
        if (lastPlayedGameId.id % 300 == 0) delay(1000 * 60 * 31)
        println("lastFetchedGameId: $lastFetchedGameId")
        println("lastPlayedGameId: $lastPlayedGameId")
        val gameIdsToRequest = ((lastFetchedGameId.id + 1)..(lastPlayedGameId.id))
            .minus(gameIdsToExclude)
        gameIdsToRequest.forEach { gameIdToRequest ->
            val gameId = GameId(id = gameIdToRequest)
            println("gameId: ${gameId.id} of ${lastPlayedGameId.id}")
            when (val result = gameRepository.fetchGame(gameId)) {
                is FetchGameResult.Success -> gameRepository.saveGame(gameId, result.json)
                is FetchGameResult.Error -> gameRepository.storeError(gameId, result.error)
            }
            gameRepository.updateLastRequestedGameId(gameId)
            delay(1220)
        }
    }
}

sealed class FetchGameResult {
    data class Success(val json: String) : FetchGameResult()
    data class Error(val error: GameError) : FetchGameResult()
}

enum class GameError { RECOVERABLE, UNRECOVERABLE }
