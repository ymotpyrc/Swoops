package com.ymotpyrc.swoops.domain.games.models

import com.ymotpyrc.swoops.domain.players.usecases.GameId
import kotlinx.datetime.Instant

data class TeamId(val id: Int)

data class Game(
    val id: GameId,
    val lineups: Map<TeamId, Lineup>,
    val results: Results,
    val playedAt: Instant,
)