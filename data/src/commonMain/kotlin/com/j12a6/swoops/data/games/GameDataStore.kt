package com.j12a6.swoops.data.games

import com.j12a6.swoops.data.PathProvider
import com.j12a6.swoops.data.SwoopsApi
import com.j12a6.swoops.data.appendTextToFile
import com.j12a6.swoops.data.games.model.GameDto
import com.j12a6.swoops.data.games.model.OpenedGamesDto
import com.j12a6.swoops.data.listFilesInFolder
import com.j12a6.swoops.data.readLinesFromFile
import com.j12a6.swoops.data.readTextFromFile
import com.j12a6.swoops.data.writeTextToFile
import com.j12a6.swoops.domain.games.usecases.FetchGameResult
import com.j12a6.swoops.domain.games.usecases.GameError
import com.j12a6.swoops.domain.players.usecases.GameId
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class GameDataStore(
    private val swoopsApi: SwoopsApi,
    private val pathProvider: PathProvider,
) {

    private val json = Json { ignoreUnknownKeys = true }

    fun getGamesIdsToExclude(): Set<GameId> {
        val idsToExcludeFilePath = pathProvider.getUnrecoverableErrorsFilePath()
        return readLinesFromFile(idsToExcludeFilePath).map { it.toInt() }.toSet()
    }

    suspend fun fetchOpenedGames(): OpenedGamesDto =
        json.decodeFromString(swoopsApi.fetchOpenedGames())

    fun getLastRequestedGameId(): GameId =
        readTextFromFile(pathProvider.getLastFetchedGameIdFilePath()).toInt()

    fun updateLastRequestedGameId(gameId: GameId) =
        writeTextToFile(pathProvider.getLastFetchedGameIdFilePath(), gameId.toString())

    fun getGameIdsToRetry(): List<GameId> =
        readLinesFromFile(pathProvider.getRecoverableErrorsFilePath())
            .map { gameId -> gameId.toInt() }

    fun deleteGameIdFromToRetry(gameId: GameId) {
        val gameIds = readLinesFromFile(pathProvider.getRecoverableErrorsFilePath())
        writeTextToFile(
            pathProvider.getRecoverableErrorsFilePath(),
            gameIds.filter { it.toInt() != gameId }.joinToString("\n")
        )
    }

    suspend fun fetchGame(gameId: GameId): FetchGameResult {
        return try {
            val content = swoopsApi.getGame(gameId)
            try {
                json.decodeFromString<GameDto>(content)
                FetchGameResult.Success(content)
            } catch (e: Exception) {
                val error = getError(content)
                if (error == GameError.RECOVERABLE) println(e)
                FetchGameResult.Error(error)
            }
        } catch (e: Exception) {
            println(e)
            FetchGameResult.Error(GameError.RECOVERABLE)
        }
    }

    fun saveGame(gameId: GameId, json: String) {
        val gameFilePath = getGameFilePath(gameId)
        writeTextToFile(gameFilePath, json)
    }

    fun storeError(gameId: GameId, error: GameError) {
        val filePath = when (error) {
            GameError.UNRECOVERABLE -> pathProvider.getUnrecoverableErrorsFilePath()
            GameError.RECOVERABLE -> pathProvider.getRecoverableErrorsFilePath()
        }
        appendTextToFile(filePath, gameId.toString(), onNewLine = true)
    }

    //    fun getGames(): Sequence<GameDto> = getGameIds().mapNotNull(::getGame)
    fun getGames(): Sequence<GameDto> = getGameIds().map(::getGame)

    fun getGamesByIds(gameIds: Sequence<GameId>): Sequence<GameDto> {
//        return gameIds.mapNotNull(::getGame)
        return gameIds.map(::getGame)
    }

    private fun getGame(gameId: GameId): GameDto {
//    private fun getGame(gameId: GameId): GameDto? {
        val filePath = getGameFilePath(gameId)
        return loadJsonFromApiFile(filePath)
    }

    fun getGameFilePath(gameId: GameId): String {
        val dirPath = pathProvider.getGamesApiDirPath()
        val filename = pathProvider.getGameApiFilename(gameId)
        return "${dirPath}${filename}"
    }

    private fun loadJsonFromApiFile(filePath: String): GameDto {
        val content = readTextFromFile(filePath)
        return json.decodeFromString(content)
    }

    fun getGameIds(): Sequence<GameId> {
        val dirPath = pathProvider.getGamesApiDirPath()
        val filenames = getFilenames(dirPath)
        return filenames.map(::getGameIdFromFilename)
    }
}

private fun getFilenames(path: String): Sequence<String> =
    listFilesInFolder(path)
        .filter { it.endsWith(".json") }
        .sortedBy { it.removeSuffix(".json").toInt() }

private fun getGameIdFromFilename(filename: String): GameId =
    filename.replace(".json", "").toInt()

private fun getError(content: String): GameError {
    return when {
        content.contains(""""played_at":null""") -> GameError.UNRECOVERABLE
        content.contains(""""detail":"Not found."}""") -> GameError.UNRECOVERABLE
        content.contains(""""status":"ERROR"""") -> GameError.UNRECOVERABLE
        content.contains(""""status":"VOIDED"""") -> GameError.UNRECOVERABLE
        else -> GameError.RECOVERABLE
    }
}
