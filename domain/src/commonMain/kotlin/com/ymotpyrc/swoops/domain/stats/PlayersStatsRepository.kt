package com.ymotpyrc.swoops.domain.stats

import com.ymotpyrc.swoops.domain.stats.models.Percentile
import com.ymotpyrc.swoops.domain.stats.models.PlayerStats
import com.ymotpyrc.swoops.domain.stats.models.PlayerWithPosition

interface PlayersStatsRepository {

    fun getPlayerStats(): Map<PlayerWithPosition, PlayerStats>
    fun getPlayerStatsPercentiles(): Map<PlayerWithPosition, Map<Percentile, PlayerStats>>
    fun savePlayerStats(playerStats: Map<PlayerWithPosition, PlayerStats>)
    fun savePlayerStatsPercentiles(playerStatsPercentiles: Map<PlayerWithPosition, Map<Percentile, PlayerStats>>)
}