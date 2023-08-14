package com.j12a6.swoops.data

import com.j12a6.swoops.domain.players.usecases.GameId

class PathProvider(
    private val apiPath: String,
    private val gameApiPath: String,
    private val lastFetchedGameIdFilePath: String,
    private val unrecoverableErrorsFilePath: String,
    private val recoverableErrorsFilePath: String,
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

    fun getPlayerApiFilename(playerId: String): String = "player_${playerId}.json"

    fun getGameApiFilename(gameId: GameId): String = "${gameId}.json"

    fun getPlayersHtmlDirPath(): String =
//        "${getEnvVariable("HOME")}/Projects/databases/swoops/scrapped_html_pages/"
        "${getEnvVariable("HOME")}/Projects/Swoops/src/jvmMain/kotlin/backend/"

    fun getPlayerHtmlFilename(playerId: String): String = "player_${playerId}.html"
}

expect fun getEnvVariable(name: String): String?