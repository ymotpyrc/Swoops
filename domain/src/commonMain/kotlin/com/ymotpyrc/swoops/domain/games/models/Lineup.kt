package com.ymotpyrc.swoops.domain.games.models

import kotlinx.serialization.Serializable

@Serializable
data class PlayerId(val id: Int)

data class Lineup(
    val teamId: TeamId,
    val teamComposition: List<PlayerId>,
    val playersStats: Map<PlayerId, LineupPlayerStats>,
)