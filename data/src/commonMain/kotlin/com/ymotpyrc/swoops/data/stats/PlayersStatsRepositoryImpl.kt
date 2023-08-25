package com.ymotpyrc.swoops.data.stats

import com.ymotpyrc.swoops.data.stats.models.PlayersStatsDto
import com.ymotpyrc.swoops.data.stats.models.PlayersStatsPercentilesDto
import com.ymotpyrc.swoops.domain.stats.PlayersStatsRepository
import com.ymotpyrc.swoops.domain.stats.models.Percentile
import com.ymotpyrc.swoops.domain.stats.models.PlayerStats
import com.ymotpyrc.swoops.domain.stats.models.PlayerWithPosition

class PlayersStatsRepositoryImpl(
    private val playersStatsDataStore: PlayersStatsDataStore,
) : PlayersStatsRepository {

    override fun getPlayerStats(): Map<PlayerWithPosition, PlayerStats> =
        playersStatsDataStore.getStats().stats

    override fun getPlayerStatsPercentiles(): Map<PlayerWithPosition, Map<Percentile, PlayerStats>> =
        playersStatsDataStore.getStatsPercentiles().stats

    override fun savePlayerStats(playerStats: Map<PlayerWithPosition, PlayerStats>) {
        playersStatsDataStore.saveStats(PlayersStatsDto(stats = playerStats))
    }

    override fun savePlayerStatsPercentiles(
        playerStatsPercentiles: Map<PlayerWithPosition, Map<Percentile, PlayerStats>>,
    ) {
        playersStatsDataStore.saveStatsPercentiles(PlayersStatsPercentilesDto(stats = playerStatsPercentiles))
    }
}
