package com.ymotpyrc.swoops.domain.stats.usecases

import com.ymotpyrc.swoops.domain.stats.PlayersStatsRepository
import com.ymotpyrc.swoops.domain.stats.models.Percentile
import com.ymotpyrc.swoops.domain.stats.models.PlayerStats
import com.ymotpyrc.swoops.domain.stats.models.PlayerWithPosition

class ExportPlayersStatsPercentilesUseCase(
    private val playersStatsRepository: PlayersStatsRepository,
) {

    fun invoke() {
        val stats = emptyMap<PlayerWithPosition, Map<Percentile, PlayerStats>>()
        playersStatsRepository.savePlayerStatsPercentiles(stats)
    }
}