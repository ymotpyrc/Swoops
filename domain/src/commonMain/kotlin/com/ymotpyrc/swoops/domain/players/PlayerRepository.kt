package com.ymotpyrc.swoops.domain.players

import com.ymotpyrc.swoops.domain.players.models.Player

interface PlayerRepository {

//    suspend fun fetchPlayers()
//    suspend fun savePlayers()
    fun getPlayers(): List<Player>
    suspend fun getPlayersFromDb(): List<Player>
    suspend fun getPlayersFromHtml(): List<Player>
    suspend fun storePlayersIntoDb(players: List<Player>)
    fun migrateFilesFromHtmlToApi()
}