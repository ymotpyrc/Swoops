package com.ymotpyrc.swoops.data.games.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LineupDto(
    @SerialName("id") val id: Int,
    @SerialName("team") val team: TeamDto,
    @SerialName("player_1") val player1: LineupPlayerStatsDto,
    @SerialName("player_2") val player2: LineupPlayerStatsDto,
    @SerialName("player_3") val player3: LineupPlayerStatsDto,
    @SerialName("player_4") val player4: LineupPlayerStatsDto,
    @SerialName("player_5") val player5: LineupPlayerStatsDto,
)
