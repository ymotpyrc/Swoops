package com.ymotpyrc.swoops.domain.players.usecases

import com.ymotpyrc.swoops.domain.players.models.Player

class FilterOnPlayerNumbersUseCase {

    fun invoke(players: List<Player>, playerNumbers: List<String>): List<Player> =
        players.filter { playerNumbers.contains(it.number) }
}
