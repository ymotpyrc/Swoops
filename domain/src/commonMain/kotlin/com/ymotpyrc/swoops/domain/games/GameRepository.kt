package com.ymotpyrc.swoops.domain.games

import com.ymotpyrc.swoops.domain.games.models.Game
import com.ymotpyrc.swoops.domain.games.usecases.FetchGameResult
import com.ymotpyrc.swoops.domain.games.usecases.GameError
import com.ymotpyrc.swoops.domain.players.usecases.GameId

interface GameRepository {

    fun getGameIdsToExclude(): Set<GameId>
    suspend fun getLastPlayedGameId(): GameId
    fun getLastRequestedGameId(): GameId
    fun updateLastRequestedGameId(gameId: GameId)
    fun getGameIdsToRetry(): List<GameId>
    fun deleteGameIdFromToRetry(gameId: GameId)
    suspend fun fetchGame(gameId: GameId): FetchGameResult
    fun saveGame(gameId: GameId, json: String)
    fun storeError(gameId: GameId, error: GameError)
    fun getGames(): Sequence<Game>
    fun getGamesByIds(gameIds: Sequence<GameId>): Sequence<Game>
    fun getGameIds(): Sequence<GameId>
}