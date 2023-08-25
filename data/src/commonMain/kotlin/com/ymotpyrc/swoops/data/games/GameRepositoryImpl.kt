package com.ymotpyrc.swoops.data.games

import com.ymotpyrc.swoops.data.games.mappers.GameMapper
import com.ymotpyrc.swoops.domain.games.GameRepository
import com.ymotpyrc.swoops.domain.games.models.Game
import com.ymotpyrc.swoops.domain.games.usecases.FetchGameResult
import com.ymotpyrc.swoops.domain.games.usecases.GameError
import com.ymotpyrc.swoops.domain.players.usecases.GameId

class GameRepositoryImpl(
    private val gameDataStore: GameDataStore,
    private val gameMapper: GameMapper,
) : GameRepository {

    override fun getGameIdsToExclude(): Set<GameId> = gameDataStore.getGamesIdsToExclude()

    override suspend fun getLastPlayedGameId(): GameId =
        GameId(id = gameDataStore.fetchOpenedGames().results.minBy { it.id }.id - 1)

    override fun getLastRequestedGameId(): GameId = gameDataStore.getLastRequestedGameId()

    override fun updateLastRequestedGameId(gameId: GameId) =
        gameDataStore.updateLastRequestedGameId(gameId)

    override fun getGameIdsToRetry(): List<GameId> = gameDataStore.getGameIdsToRetry()

    override fun deleteGameIdFromToRetry(gameId: GameId) {
        gameDataStore.deleteGameIdFromToRetry(gameId)
    }

    override suspend fun fetchGame(gameId: GameId): FetchGameResult {
        return gameDataStore.fetchGame(gameId)
    }

    override fun saveGame(gameId: GameId, json: String) {
        gameDataStore.saveGame(gameId, json)
    }

    override fun storeError(gameId: GameId, error: GameError) {
        gameDataStore.storeError(gameId, error)
    }

    override fun getGames(): Sequence<Game> {
        return gameDataStore.getGames().map(gameMapper::map)
    }

    override fun getGamesByIds(gameIds: Sequence<GameId>): Sequence<Game> {
        return gameDataStore.getGamesByIds(gameIds).map(gameMapper::map)
    }

    override fun getGameIds(): Sequence<GameId> {
        return gameDataStore.getGameIds()
    }
}
