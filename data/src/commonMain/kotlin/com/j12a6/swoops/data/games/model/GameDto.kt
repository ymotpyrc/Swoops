package com.j12a6.swoops.data.games.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
    @SerialName("id") val id: Int,
    @SerialName("lineup_1") val lineup1: LineupDto,
    @SerialName("lineup_2") val lineup2: LineupDto,
    @SerialName("contest") val contest: ContestDto,
    @SerialName("prize_pool") val prizePool: String?,
    @SerialName("results") val results: ResultsDto,
)
