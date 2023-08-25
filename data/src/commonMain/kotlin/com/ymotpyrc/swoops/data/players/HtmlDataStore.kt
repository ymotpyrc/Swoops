package com.ymotpyrc.swoops.data.players

import com.ymotpyrc.swoops.data.PathProvider
import com.ymotpyrc.swoops.data.listFilesInFolder
import com.ymotpyrc.swoops.data.players.models.PlayerDto
import com.ymotpyrc.swoops.data.players.models.TraitDto
import com.ymotpyrc.swoops.domain.games.models.PlayerId

class HtmlDataStore(private val pathProvider: PathProvider) {

    fun getPlayers(): List<PlayerDto> {
        val playerIds = getPlayerIds()
        return playerIds.map(::getPlayer)
    }

    private fun getPlayer(playerId: PlayerId): PlayerDto {
        val traits = getTraits(playerId)
        return PlayerDto(number = playerId.id.toString(), traits = traits)
    }

    private fun getPlayerIds(): List<PlayerId> {
        val dirPath = pathProvider.getPlayersHtmlDirPath()
        val filenames = getFilenames(dirPath)
        return filenames.map(::getPlayerIdFromFilename)
    }

    private fun getTraits(playerId: PlayerId): List<TraitDto> {
        val dirPath = pathProvider.getPlayersHtmlDirPath()
        val filename = pathProvider.getPlayerHtmlFilename(playerId)
        val filePath = "${dirPath}${filename}"
        return getTraitsFromFilePath(filePath)
    }
}

private fun getFilenames(path: String): List<String> =
    listFilesInFolder(path).filter { it.endsWith(".html") }.toList()

private fun getPlayerIdFromFilename(filename: String): PlayerId =
    PlayerId(id = filename.replace("player_", "").replace(".html", "").toInt())

private fun getTraitsFromFilePath(filePath: String): List<TraitDto> =
    getTraitsFromFilePath(filePath)
