package com.j12a6.swoops.domain.players

import com.j12a6.swoops.domain.players.models.Player

interface PlayerRepository {

    fun getPlayers(): List<Player>
    suspend fun getPlayersFromDb(): List<Player>
    suspend fun getPlayersFromHtml(): List<Player>
    suspend fun storePlayersIntoDb(players: List<Player>)
    fun migrateFilesFromHtmlToApi()
}