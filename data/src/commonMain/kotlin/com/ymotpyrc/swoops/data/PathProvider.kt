package com.ymotpyrc.swoops.data

import com.ymotpyrc.swoops.domain.games.models.PlayerId
import com.ymotpyrc.swoops.domain.players.usecases.GameId

class PathProvider(
    private val apiPath: String,
    private val gameApiPath: String,
    private val lastFetchedGameIdFilePath: String,
    private val unrecoverableErrorsFilePath: String,
    private val recoverableErrorsFilePath: String,
    private val playerStatsFilePath: String,
    private val playerStatsPercentilesFilePath: String,
    private val localDbPath: String,
) {

    val localDbSchema = listOf(
        getEnvVariable("HOME"),
        "Projects/Swoops/frontend/front/schema.sql",
    ).joinToString("/")

    fun getPlayersApiDirPath(): String = apiPath

    fun getGamesApiDirPath(): String = gameApiPath

    fun getLastFetchedGameIdFilePath(): String = lastFetchedGameIdFilePath

    fun getUnrecoverableErrorsFilePath(): String = unrecoverableErrorsFilePath

    fun getRecoverableErrorsFilePath(): String = recoverableErrorsFilePath

    fun getLocalDbFilePath(): String = localDbPath

    fun getPlayerApiFilename(playerId: PlayerId): String = "player_${playerId.id}.json"

    fun getGameApiFilename(gameId: GameId): String = "${gameId.id}.json"

    fun getPlayersHtmlDirPath(): String =
//        "${getEnvVariable("HOME")}/Projects/databases/swoops/scrapped_html_pages/"
        "${getEnvVariable("HOME")}/Projects/Swoops/src/jvmMain/kotlin/backend/"

    fun getPlayerHtmlFilename(playerId: PlayerId): String = "player_${playerId.id}.html"

    fun getPlayersStatsFilename(): String = playerStatsFilePath

    fun getPlayersStatsPercentilesFilename(): String = playerStatsPercentilesFilePath
}

expect fun getEnvVariable(name: String): String?