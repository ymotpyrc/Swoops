package com.ymotpyrc.swoops.domain.stats.models

import com.ymotpyrc.swoops.domain.games.models.PlayerId
import kotlinx.serialization.Serializable

@Serializable
data class PlayerWithPosition(
    val id: PlayerId,
    val playerPosition: PlayerPosition,
    val season: Season,
)

enum class PlayerPosition {
    Wherever,
    Guard,
    Forward,
    Center,
    One,
    Two,
    Three,
    Four,
    Five,
}

@Serializable
sealed interface Season {

    @Serializable
    object All : Season

    @Serializable

    data class Number(val nb: Int) : Season
}

enum class Percentile(val value: Int) {
    Five(5),
    Ten(10),
    Twenty(20),
    Thirty(30),
    Fifty(50),
    Seventy(70),
    Ninety(90),
}
