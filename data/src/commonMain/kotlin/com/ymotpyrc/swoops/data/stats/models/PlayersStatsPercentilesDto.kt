package com.ymotpyrc.swoops.data.stats.models

import com.ymotpyrc.swoops.domain.stats.models.Percentile
import com.ymotpyrc.swoops.domain.stats.models.PlayerStats
import com.ymotpyrc.swoops.domain.stats.models.PlayerWithPosition
import kotlinx.serialization.Serializable

@Serializable
data class PlayersStatsPercentilesDto(
    val stats: Map<PlayerWithPosition, Map<Percentile, PlayerStats>>,
)
