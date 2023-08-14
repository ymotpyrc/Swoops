package com.j12a6.swoops.domain.games.models

import kotlinx.datetime.Instant

typealias TeamId = Int

data class Game(
    val id: Int,
    val lineups: Map<TeamId, Lineup>,
    val results: Results,
    val playedAt: Instant,
)