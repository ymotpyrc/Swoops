package com.ymotpyrc.swoops.domain.players.usecases

import com.ymotpyrc.swoops.domain.games.models.PlayerId
import com.ymotpyrc.swoops.domain.stats.models.PlayerStats

class FilterOnSpecificStatsUseCase {

    fun invoke(players: Map<PlayerId, PlayerStats>): Map<PlayerId, PlayerStats> =
        players.filterValues { player ->
            // https://opensea.io/0x49E9693ED7Dc629dfb43d4eE2eC7B9D0EBC01ba8
//            val playerIds = listOf(219)
//            val playerIds = listOf(1227)
//            playerIds.contains(player.token)
//            player.threePointAttempts > 7 &&
//            player.threePoints.toFloat() / player.threePointAttempts > 0.4
            player.fieldGoalAttempts.value < 10 &&
                player.rebounds.value > 10
        }
    // 585, 584, 844, 582, 66
}
