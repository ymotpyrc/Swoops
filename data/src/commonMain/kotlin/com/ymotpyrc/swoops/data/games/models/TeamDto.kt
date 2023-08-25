package com.ymotpyrc.swoops.data.games.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("wins") val wins: Int,
    @SerialName("losses") val losses: Int,
)
