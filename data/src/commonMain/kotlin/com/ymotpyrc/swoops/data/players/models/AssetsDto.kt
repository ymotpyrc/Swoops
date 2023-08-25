package com.ymotpyrc.swoops.data.players.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssetsDto(
    @SerialName("assets") val players: List<PlayerDto>,
)
