package com.ymotpyrc.swoops.domain.players.usecases

import com.ymotpyrc.swoops.domain.players.PlayerRepository
import com.ymotpyrc.swoops.domain.players.models.Player

class GetPlayersUseCase(private val playerRepository: PlayerRepository) {

    suspend fun invoke(): List<Player> = playerRepository.getPlayersFromDb()
}
