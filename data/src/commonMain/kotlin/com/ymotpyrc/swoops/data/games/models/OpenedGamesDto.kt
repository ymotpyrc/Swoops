package com.ymotpyrc.swoops.data.games.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenedGamesDto(
    @SerialName("results") val results: List<OpenedGameDto>,
)
