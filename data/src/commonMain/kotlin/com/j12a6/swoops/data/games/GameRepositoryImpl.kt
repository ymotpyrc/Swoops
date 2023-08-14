package com.j12a6.swoops.data.games

import com.j12a6.swoops.data.games.mapper.GameMapper
import com.j12a6.swoops.domain.games.GameRepository
import com.j12a6.swoops.domain.games.models.Game
import com.j12a6.swoops.domain.games.usecases.FetchGameResult
import com.j12a6.swoops.domain.games.usecases.GameError
import com.j12a6.swoops.domain.players.usecases.GameId

class GameRepositoryImpl(
    private val gameDataStore: GameDataStore,
    private val gameMapper: GameMapper,
) : GameRepository {

    override fun getGameIdsToExclude(): Set<GameId> = gameDataStore.getGamesIdsToExclude()

    override suspend fun getLastPlayedGameId(): GameId =
        gameDataStore.fetchOpenedGames().results.minBy { it.id }.id

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
}
