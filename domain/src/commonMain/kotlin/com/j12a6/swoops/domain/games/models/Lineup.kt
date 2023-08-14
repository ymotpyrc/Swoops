package com.j12a6.swoops.domain.games.models

typealias PlayerId = Int

data class Lineup(
    val teamId: TeamId,
    val teamComposition: List<PlayerId>,
    val playersStats: Map<PlayerId, LineupPlayerStats>,
)