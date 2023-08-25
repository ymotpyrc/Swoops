package com.ymotpyrc.swoops.data

import com.ymotpyrc.swoops.data.players.models.PlayerDto
import com.ymotpyrc.swoops.domain.games.models.PlayerId
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ApiDataStore(private val pathProvider: PathProvider) {

    fun getPlayers(): List<PlayerDto> {
        val playerIds = getPlayerIds()
        return playerIds.map(::getPlayer)
    }

    private fun getPlayer(playerId: PlayerId): PlayerDto {
        val dirPath = pathProvider.getPlayersApiDirPath()
        val filename = pathProvider.getPlayerApiFilename(playerId)
        val filePath = "${dirPath}${filename}"
        return loadJsonFromApiFile(filePath)
    }

    fun savePlayers(players: List<PlayerDto>) {
        players.forEach(::savePlayer)
    }

    private fun getPlayerIds(): List<PlayerId> {
        val dirPath = pathProvider.getPlayersApiDirPath()
        val filenames = listFilesInFolder(dirPath).filter { it.endsWith(".json") }.toList()
        return filenames.map { getPlayerIdFromFilename(it) }
    }

    private fun savePlayer(player: PlayerDto) {
        val dirPath = pathProvider.getPlayersApiDirPath()
        val filename = pathProvider.getPlayerApiFilename(PlayerId(id = player.number.toInt()))
        val filePath = "${dirPath}${filename}"
        val json = Json.encodeToString(player)
        writeTextToFile(filePath, json)
    }
}

private fun getPlayerIdFromFilename(filename: String): PlayerId =
    PlayerId(id = filename.replace("player_", "").replace(".json", "").toInt())
