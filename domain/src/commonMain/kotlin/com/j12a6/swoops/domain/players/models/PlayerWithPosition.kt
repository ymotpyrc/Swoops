package com.j12a6.swoops.players.models

import com.j12a6.swoops.domain.games.models.PlayerId

data class PlayerWithPosition(val id: PlayerId, val playerPosition: PlayerPosition)

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
