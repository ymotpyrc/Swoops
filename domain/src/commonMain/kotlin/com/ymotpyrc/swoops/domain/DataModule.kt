package com.ymotpyrc.swoops.domain

import com.ymotpyrc.swoops.domain.games.GameRepository
import com.ymotpyrc.swoops.domain.players.PlayerRepository
import com.ymotpyrc.swoops.domain.stats.PlayersStatsRepository

interface DataModule {

    fun provideGameRepository() : GameRepository
    fun providePlayerRepository(): PlayerRepository
    fun providePlayersStatsRepository(): PlayersStatsRepository
}