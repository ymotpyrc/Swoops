package com.j12a6.swoops.domain.players.usecases

import com.j12a6.swoops.domain.players.PlayerRepository
import com.j12a6.swoops.domain.players.models.Player

class GetPlayersUseCase(private val playerRepository: PlayerRepository) {

    suspend fun invoke(): List<Player> = playerRepository.getPlayersFromDb()
}
