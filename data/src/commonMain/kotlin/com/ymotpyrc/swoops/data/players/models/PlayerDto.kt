package com.ymotpyrc.swoops.data.players.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDto(
    @SerialName("token_id") val number: String,
    @SerialName("traits") val traits: List<TraitDto>,
)
