package com.ymotpyrc.swoops.domain.players.usecases

import com.ymotpyrc.swoops.domain.players.PlayerRepository

class FetchPlayersUseCase(
    private val playerRepository: PlayerRepository,
) {

    suspend fun invoke() {
        println("Let's go!")
    }
}
