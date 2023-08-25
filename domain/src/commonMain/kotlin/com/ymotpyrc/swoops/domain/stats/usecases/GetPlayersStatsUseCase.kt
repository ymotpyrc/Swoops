package com.ymotpyrc.swoops.domain.stats.usecases

import com.ymotpyrc.swoops.domain.stats.PlayersStatsRepository
import com.ymotpyrc.swoops.domain.stats.models.PlayerStats
import com.ymotpyrc.swoops.domain.stats.models.PlayerWithPosition

class GetPlayersStatsUseCase(private val playersStatsRepository: PlayersStatsRepository) {

    fun invoke(): Map<PlayerWithPosition, PlayerStats> = playersStatsRepository.getPlayerStats()
}