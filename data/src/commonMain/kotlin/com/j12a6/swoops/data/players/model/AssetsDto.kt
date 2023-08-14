package com.j12a6.swoops.data.players.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssetsDto(
    @SerialName("assets") val players: List<PlayerDto>,
)
