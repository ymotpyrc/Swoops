package com.ymotpyrc.swoops.data.stats.models

import com.ymotpyrc.swoops.domain.stats.models.PlayerStats
import com.ymotpyrc.swoops.domain.stats.models.PlayerWithPosition
import kotlinx.serialization.Serializable

@Serializable
data class PlayersStatsDto(val stats: Map<PlayerWithPosition, PlayerStats>)
