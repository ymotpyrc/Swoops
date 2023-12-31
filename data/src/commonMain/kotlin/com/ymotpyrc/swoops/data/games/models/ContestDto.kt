package com.ymotpyrc.swoops.data.games.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContestDto(
    @SerialName("id") val id: Int,
    @SerialName("status") val status: String,
    @SerialName("kind") val kind: String,
    @SerialName("played_at") val playedAt: String,
)
