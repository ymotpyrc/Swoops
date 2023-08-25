package com.ymotpyrc.swoops.data.stats

import com.ymotpyrc.swoops.data.PathProvider
import com.ymotpyrc.swoops.data.readTextFromFile
import com.ymotpyrc.swoops.data.stats.models.PlayersStatsDto
import com.ymotpyrc.swoops.data.stats.models.PlayersStatsPercentilesDto
import com.ymotpyrc.swoops.data.writeTextToFile
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PlayersStatsDataStore(private val pathProvider: PathProvider) {

    private val json = Json {
        ignoreUnknownKeys = true
        allowStructuredMapKeys = true
    }

    fun getStats(): PlayersStatsDto = loadJsonFromFile(pathProvider.getPlayersStatsFilename())

    fun saveStats(playersStats: PlayersStatsDto) {
        writeTextToFile(pathProvider.getPlayersStatsFilename(), json.encodeToString(playersStats))
    }

    fun getStatsPercentiles(): PlayersStatsPercentilesDto =
        loadJsonFromFile(pathProvider.getPlayersStatsPercentilesFilename())

    fun saveStatsPercentiles(playersStatsPercentiles: PlayersStatsPercentilesDto) {
        writeTextToFile(
            pathProvider.getPlayersStatsPercentilesFilename(),
            json.encodeToString(playersStatsPercentiles),
        )
    }

    private inline fun <reified T> loadJsonFromFile(filePath: String): T {
        val content = readTextFromFile(filePath)
        return json.decodeFromString(content)
    }
}