package com.ymotpyrc.swoops.presentation

import com.ymotpyrc.swoops.domain.games.models.TeamId

data class TeamOpposition(
    val opponentTeamId: TeamId,
    val played: Int,
    val wins: Int,
    val losses: Int,
    val ties: Int,
)
