package com.ymotpyrc.swoops.domain.games.usecases

import com.ymotpyrc.swoops.domain.games.GameRepository
import com.ymotpyrc.swoops.domain.players.usecases.GameId

class CheckGamesUseCase(
    private val getGameIdsToExcludeUseCase: GetGameIdsToExcludeUseCase,
    private val gameRepository: GameRepository,
) {

    fun invoke() {
        val lastRequestedGameId = gameRepository.getLastRequestedGameId()
        val gameIdsToExclude = getGameIdsToExcludeUseCase.invoke()
        val storedGamesIds = gameRepository.getGameIds().toSet()
        val gamesIdsToRetry = gameRepository.getGameIdsToRetry().toSet()
        val allGameIds = (1..lastRequestedGameId.id).map { GameId(id = it) }.toSet()
        val missingGames = allGameIds - (storedGamesIds + gameIdsToExclude + gamesIdsToRetry)
        println("lastRequestedGameId: $lastRequestedGameId")
        println("gameIdsToExclude: ${gameIdsToExclude.count()}")
        println("storedGamesIds: ${storedGamesIds.count()}")
        println("total: ${storedGamesIds.count() + gameIdsToExclude.count()}")
        println("missingGames: ${missingGames.count()}")
        missingGames.forEach { gameId -> gameRepository.storeError(gameId, GameError.RECOVERABLE) }
//        println("missingGames: ${storedGamesIds.minus(gameIdsToExclude).sortedBy { it.id }}")
    }
}

//    fun removeGames() {
//        val toDelete = listOf(1)
//        val gameDataStore = provideGameDataStore()
//        toDelete.forEach { gameId ->
//            val filePath = gameDataStore.getGameFilePath(gameId)
//            File(filePath).delete()
//        }
//    }
