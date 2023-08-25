package com.ymotpyrc.swoops.data.games.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenedGameDto(
    @SerialName("id") val id: Int,
    @SerialName("prize_pool") val prizePool: String,
)
