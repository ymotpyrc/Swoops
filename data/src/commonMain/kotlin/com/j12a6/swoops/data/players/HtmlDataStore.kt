package com.j12a6.swoops.data.players

import com.j12a6.swoops.data.PathProvider
import com.j12a6.swoops.data.listFilesInFolder
import com.j12a6.swoops.data.players.model.PlayerDto
import com.j12a6.swoops.data.players.model.TraitDto

class HtmlDataStore(private val pathProvider: PathProvider) {

    fun getPlayers(): List<PlayerDto> {
        println("HtmlDataStore:" + this)
        val playerIds = getPlayerIds()
        return playerIds.map(::getPlayer)
    }

    private fun getPlayer(playerId: String): PlayerDto {
        val traits = getTraits(playerId)
        return PlayerDto(number = playerId, traits = traits)
    }

    private fun getPlayerIds(): List<String> {
        val dirPath = pathProvider.getPlayersHtmlDirPath()
        val filenames = getFilenames(dirPath)
        return filenames.map(::getPlayerIdFromFilename)
    }

    private fun getTraits(playerId: String): List<TraitDto> {
        val dirPath = pathProvider.getPlayersHtmlDirPath()
        val filename = pathProvider.getPlayerHtmlFilename(playerId)
        val filePath = "${dirPath}${filename}"
        return getTraitsFromFilePath(filePath)
    }
}

private fun getFilenames(path: String): List<String> =
    listFilesInFolder(path).filter { it.endsWith(".html") }.toList()


private fun getPlayerIdFromFilename(filename: String): String {
    return filename.replace("player_", "").replace(".html", "")
}

private fun getTraitsFromFilePath(filePath: String): List<TraitDto> =
    getTraitsFromFilePath(filePath)
