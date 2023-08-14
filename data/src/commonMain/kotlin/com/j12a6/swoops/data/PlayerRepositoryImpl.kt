package com.j12a6.swoops.data

import com.j12a6.swoops.data.players.HtmlDataStore
import com.j12a6.swoops.domain.players.PlayerRepository
import com.j12a6.swoops.data.players.mapper.PlayerMapper
import com.j12a6.swoops.domain.players.models.Player

class PlayerRepositoryImpl(
    private val apiDataStore: ApiDataStore,
    private val htmlDataStore: HtmlDataStore,
    private val localDbDataStore: LocalDbDataStore,
    private val mapper: PlayerMapper,
) : PlayerRepository {

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
