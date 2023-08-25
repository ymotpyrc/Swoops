package com.ymotpyrc.swoops.data

import com.ymotpyrc.swoops.domain.DataModule
import com.ymotpyrc.swoops.domain.games.GameRepository
import com.ymotpyrc.swoops.domain.players.PlayerRepository
import com.ymotpyrc.swoops.domain.stats.PlayersStatsRepository

class DefaultDataModule : DataModule {

    override fun provideGameRepository(): GameRepository = providingGameRepository()

    override fun providePlayerRepository(): PlayerRepository = providingPlayerRepository()

    override fun providePlayersStatsRepository(): PlayersStatsRepository = providingPlayersStatsRepository()
}
