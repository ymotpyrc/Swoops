package com.ymotpyrc.swoops.data.players

import com.ymotpyrc.swoops.data.ApiDataStore
import com.ymotpyrc.swoops.data.LocalDbDataStore
import com.ymotpyrc.swoops.domain.players.PlayerRepository
import com.ymotpyrc.swoops.data.players.mappers.PlayerMapper
import com.ymotpyrc.swoops.domain.players.models.Player

class PlayerRepositoryImpl(
//    private val playerDataStore: PlayerDataStore,
    private val apiDataStore: ApiDataStore,
    private val htmlDataStore: HtmlDataStore,
    private val localDbDataStore: LocalDbDataStore,
    private val mapper: PlayerMapper,
) : PlayerRepository {

//    override suspend fun fetchPlayers() {
//        playerDataStore.fetchPlayers()
//    }
//
//    override suspend fun savePlayers() {
//        playerDataStore.savePlayer()
//    }

    override fun getPlayers(): List<Player> {
        val players = apiDataStore.getPlayers()
        return players.map(mapper::map)
    }

    override suspend fun getPlayersFromDb(): List<Player> = localDbDataStore.getPlayers()

    override suspend fun getPlayersFromHtml(): List<Player> = htmlDataStore.getPlayers().map(mapper::map)

    override fun migrateFilesFromHtmlToApi() {
        val players = htmlDataStore.getPlayers()
        apiDataStore.savePlayers(players)
    }

    override suspend fun storePlayersIntoDb(players: List<Player>) = localDbDataStore.savePlayers(players)
}
