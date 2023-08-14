package com.j12a6.swoops.data.players.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDto(
    @SerialName("token_id") val number: String,
    @SerialName("traits") val traits: List<TraitDto>,
)
