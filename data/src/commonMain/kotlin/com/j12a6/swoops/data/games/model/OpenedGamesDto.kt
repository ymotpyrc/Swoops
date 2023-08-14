package com.j12a6.swoops.data.games.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenedGamesDto(
    @SerialName("results") val results: List<OpenedGameDto>,
)
