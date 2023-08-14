package com.j12a6.swoops.data.games.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenedGameDto(
    @SerialName("id") val id: Int,
    @SerialName("prize_pool") val prizePool: String,
)
