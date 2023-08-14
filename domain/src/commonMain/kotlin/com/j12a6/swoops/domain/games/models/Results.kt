package com.j12a6.swoops.domain.games.models

data class Results(
    val scores: Map<TeamId, Int>,
    val boxScores: Map<TeamId, LineupBoxScore>,
    val playersBoxScore: Map<PlayerId, LineupPlayerBoxScore>,
)
